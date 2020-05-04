package ast.codeGeneration;

import ast.FunctionCall;
import ast.Program;
import ast.definitions.Definition;
import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.expressions.Expression;
import ast.expressions.binary.Aritmetic;
import ast.expressions.binary.Comparation;
import ast.expressions.binary.Logic;
import ast.expressions.literal.LiteralCharacter;
import ast.expressions.literal.LiteralInteger;
import ast.expressions.literal.LiteralReal;
import ast.expressions.other.*;
import ast.statements.*;
import types.Type;
import types.complex.ArrayType;
import types.complex.FunctionType;
import types.record.RecordType;
import types.simple.Character;
import types.simple.Integer;
import types.simple.Real;
import types.simple.Void;

public class ExecuteCGVisitor extends AbstractCGVisitor{

    private AddressCGVisitor addressCGVisitor;
    private ValueCGVisitor valueCGVisitor;

    public ExecuteCGVisitor(CG cg) {
        super(cg);
        valueCGVisitor=new ValueCGVisitor(cg);
        addressCGVisitor=new AddressCGVisitor(cg,valueCGVisitor);
        valueCGVisitor.setAddressCGVisitor(addressCGVisitor);
    }

    /*
    execute [[Program :program -> definition*]]() =
    for(Definition d:definition*)
        if(d instanceof VarDef)
            execute[[d]]
    <call main>
    <halt>

    for(Definition d:definition*)
        if(d instanceof FunDef)
            execute[[d]]


    visit (Program p){
        for(Definition d:definition*)
            if(d instanceof VarDef)
                d.accept(this,p)
        CG.call("main");
        CG.halt();

        for(Definition d:definition*)
            if(d instanceof FunDef)
                d.accept(this,p)
    }

     */
    @Override
    public Object visit(Program a, Type param) {
        for(Definition d:a.getDefinitions())
            if(d instanceof VarDef)
                ((VarDef) d).accept(this,param);
        cg.anotation("\n' Invocation to the main function");
        cg.call("main");
        cg.halt();

        for(Definition d:a.getDefinitions())
            if(d instanceof FunDef)
                ((FunDef) d).accept(this,param);
        return null;
    }
    /*
    execute[[FunDef:fundef -> type statement*]]()=
    fundef.name <:>
    //comentamos parametros
    //Comentamos variable locales
    <enter> fundef.sumbyteslocalvar
    for(Sentencia s:sentencias)
        execute[[s]]

    if(fundef.returnType==Void)
    <ret 0,fundef.sumbyteslocalvar>
     */
    @Override
    public Object visit(FunDef a, Type param) {
        cg.line(a.getLine());
        cg.fun(a.getName());
        cg.parameters();
        for(Definition d:((FunctionType)a.getType()).getParam())
            ((VarDef)d).accept(this,param);
        cg.local();
        for(Statement s:a.getSentences())
            if(s instanceof VarDef)
                s.accept(this,param);
        cg.enter(((FunctionType)a.getType()).getBytesLocalVar());
        for(Statement s:a.getSentences())
            if(!(s instanceof VarDef))
                s.accept(this,a.getType());
        if(((FunctionType) a.getType()).getReturnType() instanceof Void)
            cg.ret(0,((FunctionType)a.getType()).getBytesLocalVar(),((FunctionType) a.getType()).getParamBytes());
        return null;
    }
    /*
    execute [[VarDef:vardef -> type]]=
    if(type==Integer)
        <int> vardef.name (offset vardef.offset)
    if(type==Real)
        <double> vardef.name (offset vardef.offset)
    if(type==Character)
        <char> vardef.name (offset vardef.offset)
     */
    @Override
    public Object visit(VarDef a, Type param) {
        if(a.getType()== Integer.getInstance()){
            cg.var(a.getType(),a.getName(),a.getOffset());
        }else if(a.getType()== Character.getInstance()){
            cg.var(a.getType(),a.getName(),a.getOffset());
        }else if(a.getType()== Real.getInstance()){
            cg.var(a.getType(),a.getName(),a.getOffset());
        }else if(a.getType() instanceof ArrayType){
            cg.var(a.getType(),a.getName(),a.getOffset());
        }else if(a.getType() instanceof RecordType){
            cg.var(a.getType(),a.getName(),a.getOffset());
        }
        return null;
    }







    /*
    execute[[Variable]]=
    valueCGVisitor.visit(Variable,param)
     */
    @Override
    public Object visit(Variable a, Type param) {
        a.accept(valueCGVisitor,param);
        return null;
    }
    /*
        execute[[Assignment:as -> expression:right expression:left]]=
        addressCGVisitor.visit(left)
        execute[right]
        <store> right.suffix

     */
    @Override
    public Object visit(Assignment a, Type param) {
        cg.line(a.getLine());
        a.getLeft().accept(addressCGVisitor,param);
        a.getRight().accept(valueCGVisitor,param);
        cg.store(a.getRight().getType());
        return null;
    }

    /*
    execute[[ArrayCharAssignment:acs -> expression:left String:right]]=
    for(int i;i<right.lenght();i++)
        addressCGVisitor.visit(left)
        <pushi> i
        <addi>
        <pushb> right.charAt(i)
        <store>
     */

    @Override
    public Object visit(ArrayCharAssignment a, Type param) {
        cg.line(a.getLine());
        for(int i=0;i<a.getArrayChar().length();i++){
            a.getLeft().accept(addressCGVisitor,param);
            cg.pushi(i);
            cg.add(Integer.getInstance());
            cg.push(a.getArrayChar().charAt(i));
            cg.store(Character.getInstance());
        }

        return null;
    }

    /*
        execute[[If :stmt -> expr stmt* stmt1*]]()=
        if(stmt1*!=null)
            int labelNumber=cg.getLabels(2);
        else
            int labelNumber=cg.getLabels(1);
        Value[[expr]]
        <jz label> labelNumber
        for(Statement stmt:stmt*)
            execute[[stmt]]
        <jmp label> labelNumber+1
        <label> labelNumber <:>
        for(Statement stmt:stmt1*)
            execute[[stmt]]
        <label> labelNumber+1

        }

         */
    @Override
    public Object visit(If a, Type param) {
        cg.line(a.getLine());
        int labelNumber;
        if(a.getDoElse()!=null)
            labelNumber=cg.getLabel(2);
        else
            labelNumber=cg.getLabel(1);
        a.getCondition().accept(valueCGVisitor,param);
        cg.jz(labelNumber+1);
        for (Statement s:a.getDoIf())
            s.accept(this,param);
        if(a.getDoElse()!=null) {
            cg.jmp(labelNumber);
            cg.label(labelNumber+1);
            for (Statement s : a.getDoElse()){
                s.accept(this,param);
            }
        }
        cg.label(labelNumber);



        return null;
    }
    /*
       execute[[Read:statement -> variable]]()=
       addressCGVisitor.visit(variable)
       <store> variable.definition.getType().suffix()
        */
    @Override
    public Object visit(Read a, Type param) {
        cg.line(a.getLine());
        cg.anotation("\n\t' * Read");
        a.getList().get(0).accept(addressCGVisitor,param);
        cg.in(a.getList().get(0).getType());
        cg.store(a.getList().get(0).getType());
        return null;
    }

    /*
    execute[[Return:stmt->expr]]()=
    <ret> expr.getType.byte ',' function.localVarBytes ',' function.paramBytes
     */
    @Override
    public Object visit(Return a, Type param) {
        cg.line(a.getLine());
        cg.anotation("\t' * Return");
        a.getExpression().accept(valueCGVisitor,param);
        cg.ret(a.getExpression().getType().size(),((FunctionType)param).getBytesLocalVar(),((FunctionType)param).getParamBytes());
        return null;
    }
    /*
    execute[[While :stmt -> expr stmt*]]()=
    int labelNumber=cg.getLabels(2);
    <label> labelNumber <:>
    Value[[expr]]
    <jz label> labelNumber+1
    for(Statement stmt:stmt*)
        execute[[stmt]]
    <jmp label> labelNumber
    <label> labelNumber+1 <:>
     */
    @Override
    public Object visit(While a, Type param) {
        cg.line(a.getLine());
        int labelNumber=cg.getLabel(2);
        cg.label(labelNumber);
        a.getCondition().accept(valueCGVisitor,param);
        cg.jz(labelNumber+1);
        for(Statement s:a.getDoWhile()){
            s.accept(this,param);
        }
        cg.jmp(labelNumber);
        cg.label(labelNumber+1);
        return null;
    }
    /*
        execute[[Write : statement -> expression*]]()=
        for(Expression exp:expression*)
            value[[exp]]
            <out> exp.type.suffix()
         */
    @Override
    public Object visit(Write a, Type param) {
        cg.line(a.getLine());
        for (Expression e:a.getList()) {
            cg.anotation("\n\t' * Write");
            e.accept(valueCGVisitor, param);
            cg.out(e);
        }
        return null;
    }

    /*
    execute[[FunctionCall: expr -> expr1 expr*]]()=
    value[[expr]]
    if(expr.type!='void')
        <pop>expr.type.suffix
     */
    @Override
    public Object visit(FunctionCall a, Type param) {
        cg.line(a.getLine());
        a.accept(valueCGVisitor,param);
        if(!(a.getType() instanceof Void))
            cg.pop(a.getType());
        return null;
    }
}

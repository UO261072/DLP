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
import types.complex.FunctionType;
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
        cg.fun(a.getName());
        cg.parameters();
        for(Definition d:((FunctionType)a.getType()).getParam())
            ((VarDef)d).accept(this,param);
        cg.local();
        for(Statement s:a.getSentences())
            if(s instanceof VarDef)
                s.accept(this,param);
        cg.enter(4);
        for(Statement s:a.getSentences())
            if(!(s instanceof VarDef))
                s.accept(this,param);
        if(((FunctionType) a.getType()).getReturnType() instanceof Void)
            cg.ret(0,a.getBytesLocalVar(),((FunctionType) a.getType()).getParamBytes());
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
            cg.var("int",a.getName(),a.getOffset());
        }else if(a.getType()== Character.getInstance()){
            cg.var("char",a.getName(),a.getOffset());
        }else if(a.getType()== Real.getInstance()){
            cg.var("double",a.getName(),a.getOffset());
        }
        return null;
    }
    /*
        execute[[Aritmetic:expression -> expression:right expression:left]]()=
        execute[right]
        execute[left]
        switch(expression.getOperator){
            case '+':
                <add> expression.getType().suffix()
            break;
            case '-':
                <sub> expression.getType().suffix()
            break;
            case '*':
                <mul> expression.getType().suffix()
            break;
            case '/':
                <div> expression.getType().suffix()
            break;
        }
         */
    @Override
    public Object visit(Aritmetic a, Type param) {
        a.getLeft().accept(this,param);
        a.getRight().accept(this,param);
        switch (a.getOperator()) {
            case "+":
                cg.add(a.getType());
                break;
            case "-":
                cg.sub(a.getType());
                break;
            case "*":
                cg.mul(a.getType());
                break;
            case "/":
                cg.div(a.getType());
                break;
        }
        return null;
    }
    /*
        execute[[Comparation:expression -> expression:right expression:left]]()=
        <push> right.getType().suffix() execute[right]
        <push> left.getType().suffix() execute[left]
        switch(expression.getOperator){
            case '>':
                <gt> expression.getType().suffix()
            break;
            case '<':
                <lt> expression.getType().suffix()
            break;
            case '>=':
                <ge> expression.getType().suffix()
            break;
            case '<=':
                <le> expression.getType().suffix()
            break;
            case '==':
                <eq> expression.getType().suffix()
            break;
            case '!=':
                <ne> expression.getType().suffix()
            break;
        }
         */
    @Override
    public Object visit(Comparation a, Type param) {
        a.getLeft().accept(this,param);
        a.getRight().accept(this,param);
        switch (a.getOperator()) {
            case ">":
                cg.gt(a.getType());
                break;
            case "<":
                cg.lt(a.getType());
                break;
            case ">=":
                cg.ge(a.getType());
                break;
            case "<=":
                cg.le(a.getType());
                break;
            case "==":
                cg.eq(a.getType());
                break;
            case "!=":
                cg.ne(a.getType());
                break;
        }
        return null;
    }
    /*
      execute[[Logic:expression -> expression:right expression:left]]()=
      <push> right.getType().suffix() execute[right]
      <push> left.getType().suffix() execute[left]
      switch(expression.getOperator){
          case '&&':
              <and>
          break;
          case '||':
              <or>
          break;
       */
    @Override
    public Object visit(Logic a, Type param) {
        a.getLeft().accept(this,param);
        a.getRight().accept(this,param);
        switch (a.getOperator()) {
            case "&&":
                cg.and();
                break;
            case "||":
                cg.or();
                break;
        }
        return null;
    }
    /*
        execute[[LiteralInteger->value]]=
        <push>LiteralInteger.getType().suffix value
         */
    @Override
    public Object visit(LiteralInteger a, Type param) {
        cg.push(a.getValue());
        return null;
    }

    /*
    execute[[LiteralCharacter->value]]=
    <push>LiteralCharacter.getType().suffix value
     */
    @Override
    public Object visit(LiteralCharacter a, Type param) {
        cg.push(a.getValue());
        return null;
    }
    /*
        execute[[LiteralReal->value]]=
        <push>LiteralReal.getType().suffix value
         */
    @Override
    public Object visit(LiteralReal a, Type param) {
        cg.push(a.getValue());
        return null;
    }
        /*
        execute[[AccesoArray]]=

         */
    @Override
    public Object visit(AccesoArray a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(AccesoCampos a, Type param) {
        return super.visit(a, param);
    }
    /*
    execute[[Cast -> value]]=
    execute[value]
    <value.suffix 2 Cast.type.suffix>
    */
    @Override
    public Object visit(Cast a, Type param) {
        a.getExpression().accept(this,param);
        cg.convert(a.getType(),a.getExpression().getType());
        return null;
    }

    /*
    execute[[MenosUnario -> value]]=
    execute[value]
    <pushi> -1
    <mul>
     */
    @Override
    public Object visit(MenosUnario a, Type param) {
        a.getExpression().accept(this,param);
        cg.pushi(-1);
        cg.mul(a.getType());
        return null;
    }
    /*
        execute[[Not:not -> expression:exp]]=
        <push> execute[exp]
        <not>
    */
    @Override
    public Object visit(Not a, Type param) {
        a.getExpression().accept(this,param);
        cg.not();
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
        a.getLeft().accept(addressCGVisitor,param);
        a.getRight().accept(this,param);
        cg.store(a.getRight().getType());
        return null;
    }

    /*
    execute[[If :stmt -> expr stmt*]]()=
    int labelNumber=cg.getLabels(1);
    Value[[expr]]
    <jz> labelNumber
    for(Statement stmt:stmt*)
        execute[[stmt]]
    <label> labelNumber <:>
     */
    @Override
    public Object visit(If a, Type param) {
        return super.visit(a, param);
    }
    /*
       execute[[Read:statement -> variable]]()=
       addressCGVisitor.visit(variable)
       <store> variable.definition.getType().suffix()
        */
    @Override
    public Object visit(Read a, Type param) {
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
        return super.visit(a, param);
    }
    /*
    execute[[While :stmt -> expr stmt*]]()=
    int labelNumber=cg.getLabels(2);
    <label> labelNumber <:>
    Value[[expr]]
    <jz label> labelNumber+1
    for(Statement stmt:stmt*)
        execute[[stmt]]
    <jmp label> labelNumber <:>
    <label> labelNumber+1 <:>
     */
    @Override
    public Object visit(While a, Type param) {
        return super.visit(a, param);
    }
    /*
        execute[[Write : statement -> expression*]]()=
        for(Expression exp:expression*)
            value[[exp]]
            <out> exp.type.suffix()
         */
    @Override
    public Object visit(Write a, Type param) {

        for (Expression e:a.getList()) {
            cg.anotation("\n\t' * Write");
            e.accept(this, param);
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
        return super.visit(a, param);
    }
}

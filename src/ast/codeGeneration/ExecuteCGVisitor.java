package ast.codeGeneration;

import ast.FunctionCall;
import ast.Program;
import ast.definitions.Definition;
import ast.definitions.FunDef;
import ast.definitions.VarDef;
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
            s.accept(this,param);
        if(((FunctionType) a.getType()).getReturnType() instanceof Void)
            cg.ret(0,a.getBytesLocalVar());
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
        <push> right.getType().suffix() execute[right]
        <push> left.getType().suffix() execute[left]
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
        return super.visit(a, param);
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
        return super.visit(a, param);
    }
    /*
      execute[[Logic:expression -> expression:right expression:left]]()=
      <push> right.getType().suffix() execute[right]
      <push> left.getType().suffix() execute[left]
      switch(expression.getOperator){
          case '&&':
              <and>
          break;
          case '<||':
              <or>
          break;
       */
    @Override
    public Object visit(Logic a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(LiteralInteger a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(LiteralCharacter a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(LiteralReal a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(AccesoArray a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(AccesoCampos a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(Cast a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(MenosUnario a, Type param) {
        return super.visit(a, param);
    }
    /*
        execute[[Not:not -> expression:exp]]=
        <push> execute[exp]
        <not>
    */
    @Override
    public Object visit(Not a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(Variable a, Type param) {
        return super.visit(a, param);
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

    @Override
    public Object visit(If a, Type param) {
        return super.visit(a, param);
    }
    /*
       execute[[Read:statement -> variable]]()=
       addressCGVisitor.visit(variable)
       <load> variable.definition.getType().suffix()
        */
    @Override
    public Object visit(Read a, Type param) {
        return super.visit(a, param);
    }

    @Override
    public Object visit(Return a, Type param) {
        return super.visit(a, param);
    }

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
        return super.visit(a, param);
    }

    @Override
    public Object visit(FunctionCall a, Type param) {
        return super.visit(a, param);
    }
}

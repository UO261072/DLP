package ast.codeGeneration;

import ast.FunctionCall;
import ast.expressions.Expression;
import ast.expressions.binary.Aritmetic;
import ast.expressions.binary.Comparation;
import ast.expressions.binary.Logic;
import ast.expressions.literal.LiteralCharacter;
import ast.expressions.literal.LiteralInteger;
import ast.expressions.literal.LiteralReal;
import ast.expressions.other.*;
import types.Type;

public class ValueCGVisitor extends AbstractCGVisitor {

    private  AddressCGVisitor addressCGVisitor;

    public ValueCGVisitor(CG cg) {
        super(cg);

        //addressCGVisitor=new AddressCGVisitor(cg);
    }
    public void setAddressCGVisitor(AddressCGVisitor addressCGVisitor){
        this.addressCGVisitor=addressCGVisitor;
    }


    /*
    value[[Variable :expression -ID]]()=

    addressCGVisitor.visit(expression)
    <load> expression.definition.getType().suffix()
     */

    @Override
    public Object visit(Variable a, Type param) {
        a.accept(addressCGVisitor,param);
        cg.load(a.definition.getType());
        return null;
    }

    /*
    value[[FunctionCall: expr -> expr1 expr*]]()=
    for(Expr e: expr*)
        value[[e]]
    <call> expr1.name
     */

    @Override
    public Object visit(FunctionCall a, Type param) {
        for (Expression e:a.getArgs())
            e.accept(this,param);
        cg.call(a.getFunction().getNombre());
        return null;
    }


    /*
    execute[[AccessoCampos:a]]()=
    address[[a]]
     */
    @Override
    public Object visit(AccesoCampos a, Type param) {
        a.accept(addressCGVisitor,param);
        cg.load(a.getType());
        return null;
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
    execute[[AccesoArray:access]]=
    address[[access]]
    */
    @Override
    public Object visit(AccesoArray a, Type param) {
        a.accept(addressCGVisitor,param);
        cg.load(a.getType());
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
            case "%":
                cg.mod(a.getType());
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
}

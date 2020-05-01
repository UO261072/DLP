package ast.codeGeneration;

import ast.FunctionCall;
import ast.expressions.Expression;
import ast.expressions.binary.Comparation;
import ast.expressions.literal.LiteralCharacter;
import ast.expressions.literal.LiteralInteger;
import ast.expressions.literal.LiteralReal;
import ast.expressions.other.AccesoCampos;
import ast.expressions.other.Cast;
import ast.expressions.other.Variable;
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
}

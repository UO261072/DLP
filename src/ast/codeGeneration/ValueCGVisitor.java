package ast.codeGeneration;

import ast.FunctionCall;
import ast.expressions.Expression;
import ast.expressions.binary.Comparation;
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


}

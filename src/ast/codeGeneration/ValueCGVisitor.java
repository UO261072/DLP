package ast.codeGeneration;

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
        cg.load(a);
        return null;
    }
}

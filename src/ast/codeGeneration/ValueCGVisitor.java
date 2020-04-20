package ast.codeGeneration;

public class ValueCGVisitor extends AbstractCGVisitor {

    private  AddressCGVisitor addressCGVisitor;

    public ValueCGVisitor(CG cg) {
        super(cg);
        addressCGVisitor=new AddressCGVisitor(cg);
    }


    /*
    value[[Variable :expression -ID]]()=

    addressCGVisitor.visit(expression)
    <load> expression.definition.getType().suffix()
     */
}

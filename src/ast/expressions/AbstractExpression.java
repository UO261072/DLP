package ast.expressions;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import types.Type;

public class AbstractExpression extends AbstractASTNode implements Expression{
    private Type type;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type t) {
        type=t;
    }

    @Override
    public boolean isLValue() {
        return false;
    }


    @Override
    public Object accept(Visitor v, Object o) {
        return null;
    }
}

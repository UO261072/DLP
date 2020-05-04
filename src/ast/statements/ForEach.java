package ast.statements;

import ast.AbstractASTNode;
import ast.expressions.Expression;
import ast.visitor.Visitor;
import types.Type;

import java.util.List;

public class ForEach  extends AbstractASTNode implements Statement  {

    private Expression left;
    private Expression right;
    private List<Statement> doForEach;

    public ForEach(int line, int column, Expression left, Expression right, List<Statement> doForEach) {
        super(line, column);
        this.right=right;
        this.left=left;
        this.doForEach = doForEach;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public List<Statement> getDoForEach() {
        return doForEach;
    }

    public void setDoForEach(List<Statement> doForEach) {
        this.doForEach = doForEach;
    }

    @Override
    public Object accept(Visitor v, Object o) {
        v.visit(this,(Type)o);
        return null;
    }
}

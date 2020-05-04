package ast.statements;

import ast.AbstractASTNode;
import ast.expressions.Expression;
import ast.visitor.Visitor;
import types.Type;

public class ArrayCharAssignment extends AbstractASTNode implements Statement{


    private Expression left;
    private String arrayChar;


    public ArrayCharAssignment(int line, int column, Expression left, String arrayChar) {
        super(left.getLine(), left.getColumn());
        this.left = left;
        this.arrayChar = arrayChar.substring(1,arrayChar.length()-1);
    }
    @Override
    public Object accept(Visitor v, Object o) {
        v.visit(this,(Type)o);
        return null;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public String getArrayChar() {
        return arrayChar;
    }

    public void setArrayChar(String right) {
        this.arrayChar = arrayChar;
    }
}

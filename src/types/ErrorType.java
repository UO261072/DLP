package types;

import ast.ASTNode;
import ast.AbstractASTNode;
import ast.visitor.Visitor;

public class ErrorType  extends AbstractASTNode {

    private String message;

    public ErrorType(int line, int column, String message) {
        super(line, column);
        this.message = message;
    }

    @Override
    public Object accept(Visitor v, Object o) {
        return null;
    }

    @Override
    public String toString() {
        return "Error en linea "+getLine()+ " columna "+getColumn()+" : "+message;
    }
}

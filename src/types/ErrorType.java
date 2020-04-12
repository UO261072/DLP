package types;

import ast.ASTNode;
import ast.AbstractASTNode;
import ast.ErrorHandler;
import ast.visitor.Visitor;

public class ErrorType  extends AbstractType implements ASTNode {

    private String message;
    int line;
    int column;

    public ErrorType(int line, int column, String message) {
        this.line=line;
        this.column=column;
        this.message = message;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
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

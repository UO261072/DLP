package ast.expressions;


import ast.AbstractASTNode;
import ast.ASTNode;
import types.Type;

public interface Expression extends ASTNode {

    Type getType();
    void setType(Type t);
    boolean isLValue();

}

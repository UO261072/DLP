package ast.expressions;


import ast.AbstractASTNode;
import ast.ASTNode;

public interface Expression extends ASTNode {

    boolean isLValue();

}

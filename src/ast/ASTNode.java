package ast;

import ast.visitor.Visitor;

public interface ASTNode {
	int getLine();
	int getColumn();

	public Object accept(Visitor v, Object o);
}

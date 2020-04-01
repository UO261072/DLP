package ast.statements;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class Assignment extends AbstractASTNode implements Statement {

	private Expression left;
	private Expression right;

	
	public Assignment(int line, int column, Expression left, Expression right) {
		super(line, column);
		this.left = left;
		this.right = right;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
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
}

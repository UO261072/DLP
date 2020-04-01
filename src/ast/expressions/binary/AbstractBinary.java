package ast.expressions.binary;

import ast.AbstractASTNode;
import ast.expressions.Expression;

public abstract class AbstractBinary extends AbstractASTNode implements Expression{
	
	private String operator;
	private Expression left;
	private Expression right;
	private boolean LValue;
	
	public AbstractBinary(int line, int column, String operator, Expression left, Expression right) {
		super(left.getLine(), left.getColumn());
		this.operator=operator;
		this.left=left;
		this.right=right;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public boolean isLValue() {
		return LValue;
	}
	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}


}


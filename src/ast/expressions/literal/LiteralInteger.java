package ast.expressions.literal;

import ast.AbstractASTNode;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class LiteralInteger extends AbstractExpression implements Expression {

	private int value;
	private boolean LValue;
	
	public LiteralInteger(int line, int column,int value) {
		super(line, column);
		this.value=value;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

package ast.expressions.literal;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class LiteralReal extends AbstractASTNode implements Expression {

	private double value;
	private boolean LValue;
	
	public LiteralReal(int line, int column,double value) {
		super(line, column);
		this.value=value;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

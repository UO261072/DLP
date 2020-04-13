package ast.expressions.other;

import ast.AbstractASTNode;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class AccesoArray extends AbstractExpression implements Expression {

	private Expression array;
	private Expression location;
	private boolean LValue;
	
	public AccesoArray(int line, int column, Expression array, Expression location) {
		super(array.getLine(), array.getColumn());
		this.array = array;
		this.location = location;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public Expression getArray() {
		return array;
	}

	public void setArray(Expression array) {
		this.array = array;
	}

	public Expression getLocation() {
		return location;
	}

	public void setLocation(Expression location) {
		this.location = location;
	}

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

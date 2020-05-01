package ast.expressions.other;

import ast.AbstractASTNode;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;
import types.Type;

public class Cast extends AbstractExpression implements Expression {

	private Expression expression;
	private Type type;
	private boolean LValue;
	
	public Cast(int line, int column, Expression expression, Type type) {
		super(expression.getLine(), expression.getColumn());
		this.expression = expression;
		this.type = type;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

package ast.expressions.other;

import ast.AbstractASTNode;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class Not extends AbstractExpression implements Expression {

	private Expression expression;
	private boolean LValue;

	public Not(int line, int column, Expression expression) {
		super(expression.getLine(), expression.getColumn());
		this.expression = expression;
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

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

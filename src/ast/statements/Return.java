package ast.statements;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class Return extends AbstractASTNode implements Statement {

	private Expression expression;

	public Return(int line, int column, Expression expression) {
		super(line, column);
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
}

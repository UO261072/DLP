package ast.statements;

import java.util.List;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class While extends AbstractASTNode implements Statement {

	private Expression condition;
	private List<Statement> doWhile;
	
	public While(int line, int column, Expression condition, List<Statement> doWhile) {
		super(condition.getLine(), condition.getColumn());
		this.condition = condition;
		this.doWhile = doWhile;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getDoWhile() {
		return doWhile;
	}

	public void setDoWhile(List<Statement> doWhile) {
		this.doWhile = doWhile;
	}
}

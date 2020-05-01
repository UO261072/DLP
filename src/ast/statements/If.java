package ast.statements;

import java.util.List;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;
import types.Type;

public class If extends AbstractASTNode implements Statement {

	private Expression condition;
	private List<Statement> doIf;
	private List<Statement> doElse;

	
	public If(int line, int column, Expression condition, List<Statement> doIf, List<Statement> doElse) {
		super(condition.getLine(), condition.getColumn());
		this.condition = condition;
		this.doIf = doIf;
		this.doElse = doElse;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,(Type)o);
		return null;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getDoIf() {
		return doIf;
	}

	public void setDoIf(List<Statement> doIf) {
		this.doIf = doIf;
	}

	public List<Statement> getDoElse() {
		return doElse;
	}

	public void setDoElse(List<Statement> doElse) {
		this.doElse = doElse;
	}
}

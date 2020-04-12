package ast;
import java.util.List;

import ast.definitions.Definition;
import ast.expressions.AbstractExpression;
import ast.expressions.Expression;
import ast.expressions.other.Variable;
import ast.statements.Statement;
import ast.visitor.Visitor;

public class FunctionCall extends AbstractExpression implements Expression, Statement {

	private Variable function;
	private List<Expression> args;
	private boolean LValue;
	public Definition definition;
	
	public FunctionCall(int line, int column, Variable function, List<Expression> args) {
		super(function.getLine(), function.getColumn());
		this.function = function;
		this.args = args;
	}

	public Variable getFunction() {
		return function;
	}

	public void setFunction(Variable function) {
		this.function = function;
	}

	public List<Expression> getArgs() {
		return args;
	}

	public void setArgs(List<Expression> args) {
		this.args = args;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}
	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

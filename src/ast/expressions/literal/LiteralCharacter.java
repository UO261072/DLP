package ast.expressions.literal;

import ast.AbstractASTNode;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class LiteralCharacter extends AbstractExpression implements Expression {

	private char value;
	private boolean LValue;

	public LiteralCharacter(int line, int column,char value) {
		super(line, column);
		this.value=value;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

package ast.expressions.binary;

import ast.visitor.Visitor;
import ast.expressions.Expression;

public class Comparation extends AbstractBinary implements Expression {

	
	
	public Comparation(int line, int column, String operator, Expression left, Expression right) {
		super(line, column, operator, left, right);
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}
}

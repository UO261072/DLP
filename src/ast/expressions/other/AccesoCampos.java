package ast.expressions.other;

import ast.AbstractASTNode;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;


public class AccesoCampos extends AbstractExpression implements Expression {

	private Expression nombre;
	private Expression expression;
	private boolean LValue;
	
	public AccesoCampos(int line, int column, Expression nombre, Expression expression) {
		super(nombre.getLine(), nombre.getColumn());
		this.nombre = nombre;
		this.expression = expression;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public Expression getNombre() {
		return nombre;
	}

	public void setNombre(Variable nombre) {
		this.nombre = nombre;
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

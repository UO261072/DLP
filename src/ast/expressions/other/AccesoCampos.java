package ast.expressions.other;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;

public class AccesoCampos extends AbstractASTNode implements Expression {

	private String nombre;
	private Expression expression;
	private boolean LValue;
	
	public AccesoCampos(int line, int column, String nombre, Expression expression) {
		super(line, column);
		this.nombre = nombre;
		this.expression = expression;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
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

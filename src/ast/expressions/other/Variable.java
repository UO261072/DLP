package ast.expressions.other;

import ast.AbstractASTNode;
import ast.definitions.Definition;
import ast.expressions.AbstractExpression;
import ast.visitor.Visitor;
import ast.expressions.Expression;
import types.Type;

public class Variable extends AbstractExpression implements Expression {

	private String nombre;
	private boolean LValue;
	public Definition definition;
	private Type type;

	public Variable(int line, int column, String nombre) {
		super(line, column);
		this.nombre = nombre;
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

	@Override
	public Type getType() {
		return definition.getType();
	}

	@Override
	public void setType(Type t) {
		this.type=t;
	}

	public boolean isLValue() {
		return LValue;
	}

	public void setLValue(boolean LValue) {
		this.LValue = LValue;
	}
}

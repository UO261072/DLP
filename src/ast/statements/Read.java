package ast.statements;

import java.util.List;

import ast.AbstractASTNode;
import ast.visitor.Visitor;
import ast.expressions.Expression;
import types.Type;

public class Read extends AbstractASTNode implements Statement {

	private List<Expression> list;

	public Read(int line, int column, List<Expression> list) {
		super(list.get(0).getLine(), list.get(0).getColumn());
		this.list = list;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,(Type)o);
		return null;
	}

	public List<Expression> getList() {
		return list;
	}

	public void setList(List<Expression> list) {
		this.list = list;
	}
}

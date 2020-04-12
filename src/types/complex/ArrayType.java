package types.complex;

import ast.ASTNode;
import ast.visitor.Visitor;
import types.AbstractType;
import types.ErrorType;
import types.Type;
import types.simple.Integer;

public class ArrayType extends AbstractType implements Type {

	private int size;
	private Type type;
	
	public ArrayType(int size, Type type) {
		super();
		this.size = size;
		this.type = type;
	}
	@Override
	public Type indexing(Type t, ASTNode node){
		if(t instanceof  ErrorType)
			return t;
		if(t==Integer.getInstance())
			return this.type;
		return super.indexing(t,node);
	}



}

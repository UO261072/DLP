package types.complex;

import ast.visitor.Visitor;
import types.AbstractType;
import types.Type;

public class ArrayType extends AbstractType implements Type {

	private int size;
	private Type type;
	
	public ArrayType(int size, Type type) {
		super();
		this.size = size;
		this.type = type;
	}



}

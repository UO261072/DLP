package types.complex;

import ast.ASTNode;
import ast.expressions.Expression;
import ast.visitor.Visitor;
import types.AbstractType;
import types.ErrorType;
import types.Type;
import types.record.RecordField;
import types.record.RecordType;
import types.simple.Character;
import types.simple.Integer;

public class ArrayType extends AbstractType implements Type {

	private int size;
	private Type type;
	
	public ArrayType(int size, Type type) {
		super();
		this.size = size;
		this.type = type;
		//ordenar();
	}
	@Override
	public Type indexing(Type t, ASTNode node){
		if(t instanceof  ErrorType)
			return t;
		if(t==Integer.getInstance())
			return this.type;
		return super.indexing(t,node);
	}

	@Override
	public Type arrayCharAssignation(String array, ASTNode node) {
		if(this.getType()== Character.getInstance())
			if(array.length()<=this.size)
				return type;
			else
				return new ErrorType(node.getLine(),node.getColumn(),"La longitud del String no puede ser mayor que la del array");
		return super.arrayCharAssignation(array, node);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/*public void ordenar(){
		if(this.type instanceof ArrayType){
			int x=this.size;
			this.size=((ArrayType)this.type).getSize();
			((ArrayType)this.getType()).setSize(x);
			((ArrayType)this.getType()).ordenar();
		}
	}*/


	@Override
	public String toString() {
		return "["+size+","+type+"]";
	}

	@Override
	public int size() {
		return type.size()*size;
	}

	@Override
	public Type acceso(String name, ASTNode node) {
		if(getType() instanceof RecordType)
			return getType().acceso(name, node);
		return super.acceso(name, node);
	}

	@Override
	public int dirNum(Expression e) {
		if(getType() instanceof RecordType)
			return getType().dirNum(e);
		return super.dirNum(e);
	}


}

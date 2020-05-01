package types.record;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.expressions.Expression;
import ast.expressions.other.Variable;
import types.AbstractType;
import types.ErrorType;
import types.Type;

public class RecordType extends AbstractType implements Type {

	private List<RecordField> components;


	public RecordType(List<RecordField> components) {
		super();
		this.components = components;
	}

	public List<RecordField> getComponents() {
		return components;
	}

	public void setComponents(List<RecordField> components) {
		this.components = components;
	}

	@Override
	public int size() {
		int size=0;
		for(RecordField f:components)
			size+=f.getType().size();
		return size;
	}

	@Override
	public Type correctStruct(ASTNode node) {
		for(int i=0;i<components.size();i++){
			for(int j=i+1;j<components.size();j++){
				if(components.get(i).getName().equals(components.get(j).getName()))
					return new ErrorType(node.getLine(),node.getColumn(),"Los componentes del struct tienen que tener nombres diferentes");
			}
		}
		return null;
	}

	@Override
	public Type acceso(String name,ASTNode node) {
		for(int i=0;i<components.size();i++){
			if(components.get(i).getName().equals(name))
				return components.get(i).getType();
		}
		return new ErrorType(node.getLine(),node.getColumn(),"El campo no existe");
	}

	@Override
	public int dirNum(Expression e) {

		for(RecordField rt:components){
			if(rt.getName().equals(((Variable)e).getNombre()))
				return rt.getOffset();
		}
		return -1;
	}


	@Override
	public String toString() {
		String a="record(";
		for (int i=0;i<components.size()-1;i++){
			a+=components.get(i);
			a+="x";
		}
		a+=components.get(components.size()-1);
		a+=")";
		return a;
	}
}

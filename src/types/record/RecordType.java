package types.record;

import java.util.List;

import ast.ASTNode;
import types.AbstractType;
import types.ErrorType;
import types.Type;

public class RecordType extends AbstractType implements Type {

	private List<RecordField> components;

	public RecordType(List<RecordField> components) {
		super();
		this.components = components;
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
}

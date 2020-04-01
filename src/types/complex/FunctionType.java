package types.complex;

import java.util.List;

import ast.definitions.VarDef;
import types.AbstractType;
import types.Type;

public class FunctionType extends AbstractType implements Type{

	private Type returnType;
	private List<VarDef> param;
	
	public FunctionType(Type returnType, List<VarDef> param) {
		super();
		this.returnType = returnType;
		this.param = param;
	}
	
	
}

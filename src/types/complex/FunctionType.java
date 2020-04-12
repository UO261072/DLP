package types.complex;

import java.util.List;

import ast.ASTNode;
import ast.definitions.VarDef;
import ast.expressions.Expression;
import types.AbstractType;
import types.ErrorType;
import types.Type;

public class FunctionType extends AbstractType implements Type{

	private Type returnType;
	private List<VarDef> param;
	
	public FunctionType(Type returnType, List<VarDef> param) {
		super();
		this.returnType = returnType;
		this.param = param;
	}

	public List<VarDef> getParam() {
		return param;
	}

	public void setParam(List<VarDef> param) {
		this.param = param;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	@Override
	public Type parentheisis(List<Expression> t, ASTNode node) {
		if(getParam().size()!=t.size())
			return new ErrorType(node.getLine(),node.getColumn(),"El numero de prametros de la funcion no es correcto");
		for(int i=0;i<getParam().size();i++){
			if(t.get(i).getType() instanceof ErrorType)
				return t.get(i).getType();
			if (getParam().get(i).getType()!=t.get(i).getType())
				return new ErrorType(node.getLine(),node.getColumn(),"El tipo de los parametros de la funcion y los argumentos no coencide");
		}
		return getReturnType();
	}
}

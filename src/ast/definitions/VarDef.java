package ast.definitions;
import ast.visitor.Visitor;
import ast.statements.Statement;
import types.Type;

public class VarDef extends AbstractDefinition implements Statement {

	private int scope;
	private int offset;
	private boolean param=false;
	
	public VarDef(int line, int column, String name, Type type) {
		super(line, column, name, type);
	}



	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,(Type) o);
		return null;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public boolean isParam(){return param;}

	public void setParam(boolean param){this.param=param;}
}

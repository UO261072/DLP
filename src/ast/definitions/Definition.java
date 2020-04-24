package ast.definitions;

import types.Type;

public interface Definition {

	public String getName();
	public Type getType();
	public int getScope();
	public void setScope(int scope);
	public int getOffset();

	public void setOffset(int offset);

	
}

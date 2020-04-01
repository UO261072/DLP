package ast.definitions;
import ast.AbstractASTNode;
import types.Type;

public abstract class AbstractDefinition extends AbstractASTNode implements Definition{
	
	private String name;
	private Type type;
	
	
	public AbstractDefinition(int line, int column, String name, Type type) {
		super(line, column);
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public Type getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Type type) {
		this.type = type;
	}
}

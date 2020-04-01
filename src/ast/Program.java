package ast;

import java.util.List;

import ast.definitions.AbstractDefinition;
import ast.visitor.Visitor;

public class Program extends AbstractASTNode{

	private List<AbstractDefinition> definitions;


	public Program(int line,int column,List<AbstractDefinition> definitions) {
		super(line,column);
		this.definitions = definitions;
	}
	
	public void addDefinition(AbstractDefinition definition){
		this.definitions.add(definition);
	}
	public void addDefinitions(List<AbstractDefinition> definitions){
		this.definitions.addAll(definitions);
	}

	public List<AbstractDefinition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<AbstractDefinition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}
}

package ast.definitions;
import java.util.List;

import ast.visitor.Visitor;
import ast.statements.Statement;
import types.Type;

public class FunDef extends AbstractDefinition {

	private List<Statement> sentences;

	public FunDef(int line, int column, String name, Type type, List<Statement> sentences) {
		super(line, column, name, type);
		this.sentences = sentences;
	}

	public List<Statement> getSentences() {
		return sentences;
	}

	public void setSentences(List<Statement> sentences) {
		this.sentences = sentences;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		v.visit(this,null);
		return null;
	}
}

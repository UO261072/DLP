package simbolTable;

import java.util.*;
import ast.definitions.Definition;
import ast.definitions.VarDef;

public class SymbolTable {
	
	private int scope=-1;
	//private List<Map<String,Definition>> table;
	private Stack<Map<String,Definition>> table;
	public SymbolTable()  {
		table=new Stack<Map<String,Definition>>();
		set();
	}

	public void set() {
		table.push(new HashMap<String,Definition>());
		this.scope++;
	}
	
	public void reset() {
		table.pop();
	}
	
	public boolean insert(Definition definition) {
		if(findInCurrentScope(definition.getName())!=null)
			return false;
		else{
			if(definition instanceof  VarDef){
				((VarDef) definition).setScope(this.scope);
			}
			table.peek().put(definition.getName(),definition);
			return true;
		}
	}
	
	public Definition find(String id) {
		for(int i=table.size()-1;i>=0;i--){
			Map<String,Definition> ambito=table.get(i);
			Definition def=ambito.get(id);
			if(def!=null)
				return def;
		}
		return null;
	}

	public Definition findInCurrentScope(String id) {
		Map<String,Definition> ambito=table.get(table.size()-1);
		Definition def=ambito.get(id);
		if(def!=null)
			return def;

		return null;
	}
}

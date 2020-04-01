package simbolTable;

import ast.definitions.VarDef;

public class SymbolTableTest {
		
	public void testInsert() {
		SymbolTable st = new SymbolTable();
		VarDef definition = new VarDef(0, 0, "a", null);
		assert st.insert(definition);
		assert definition.getScope()==0;
		assert !st.insert(definition);
		st.set();
		VarDef definition2 = new VarDef(0, 0, "a", null);
		assert st.insert(definition2);
		assert definition2.getScope()==1;
		assert !st.insert(definition2);
		st.reset();
		assert !st.insert(definition);
	}
	
	public void testFind() {
		SymbolTable st = new SymbolTable();
		VarDef varDefinition = new VarDef(0, 0, "a", null);
		assert st.insert(varDefinition);
		assert st.find("a")!=null;
		assert st.find("b")==null;
		st.set();
		VarDef varDefinition2 = new VarDef(0, 0, "b", null);
		assert st.insert(varDefinition2);
		assert st.find("b")!=null;
		assert st.find("a")!=null;
		assert st.find("c")==null;
		st.reset();
		assert st.find("a")!=null;
		assert st.find("b")==null;
	}

	public void testFindInCurrentScope() {
		SymbolTable st = new SymbolTable();
		VarDef varDefinition = new VarDef(0, 0, "a", null);
		assert st.insert(varDefinition);
		assert st.findInCurrentScope("a")!=null;
		assert st.findInCurrentScope("b")==null;
		st.set();
		VarDef varDefinition2 = new VarDef(0, 0, "b", null);
		assert st.insert(varDefinition2);
		assert st.findInCurrentScope("b")!=null;
		assert st.findInCurrentScope("a")==null;
		assert st.findInCurrentScope("c")==null;
		st.reset();
		assert st.findInCurrentScope("a")!=null;
		assert st.findInCurrentScope("b")==null;		
	}
	
	public static void main(String[] args) {
		SymbolTableTest test = new SymbolTableTest();
		test.testInsert();
		test.testFind();
		test.testFindInCurrentScope();
	}
	
}

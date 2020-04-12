package ast.visitor.identification;

import ast.ErrorHandler;
import ast.FunctionCall;
import ast.Program;
import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.expressions.other.Variable;
import ast.visitor.AbstractVisitor;
import ast.visitor.Visitor;
import simbolTable.SymbolTable;
import types.ErrorType;
import types.Type;

public class VisitorIdentification extends AbstractVisitor implements Visitor {
    private SymbolTable st=new SymbolTable();
    int scope=0;

    @Override
    public Object visit(FunDef a, Type p) {
        //System.out.println(a.getName()+" insertado en "+scope);
        if(!st.insert(a))
            new ErrorType(a.getLine(),a.getColumn(),"El nombre "+a.getName()+" ya esta definido en el scope");
        st.set();
        scope++;
        super.visit(a, p);
        st.reset();
        scope--;
        return null;
    }

    @Override
    public Object visit(VarDef a,Type p) {
        //System.out.println(a.getName()+" insertado en "+scope);
        if(!st.insert(a))
            new ErrorType(a.getLine(),a.getColumn(),"El nombre "+a.getName()+" ya esta definido en el scope");
        super.visit(a, p);
        return null;
    }

    @Override
    public Object visit(Variable a, Type p) {
        if(st.find(a.getNombre())==null)
            new ErrorType(a.getLine(),a.getColumn(),"Esta variable no ha sido definida");
        else
            a.definition=st.find(a.getNombre());
        return super.visit(a, p);
    }

}

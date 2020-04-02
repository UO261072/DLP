package ast.visitor.identification;

import ast.ErrorHandler;
import ast.Program;
import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.visitor.AbstractVisitor;
import ast.visitor.Visitor;
import simbolTable.SymbolTable;
import types.ErrorType;

public class VisitorIdentification extends AbstractVisitor implements Visitor {
    private SymbolTable st=new SymbolTable();
    int scope=0;

    @Override
    public Object visit(FunDef a, Object p) {
        System.out.println(a.getName()+" insertado en "+scope);
        if(!st.insert(a))
            ErrorHandler.getInstance().addError(new ErrorType(a.getLine(),a.getColumn(),"El nombre "+a.getName()+" ya esta definido en el scope"));
        st.set();
        scope++;
        super.visit(a, p);
        st.reset();
        return null;
    }

    @Override
    public Object visit(VarDef a, Object p) {
        System.out.println(a.getName()+" insertado en "+scope);
        if(!st.insert(a))
            ErrorHandler.getInstance().addError(new ErrorType(a.getLine(),a.getColumn(),"El nombre "+a.getName()+" ya esta definido en el scope"));
        super.visit(a, p);
        return null;
    }
}

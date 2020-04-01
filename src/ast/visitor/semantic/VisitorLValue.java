package ast.visitor.semantic;

import ast.ErrorHandler;
import ast.FunctionCall;
import ast.expressions.binary.Aritmetic;
import ast.expressions.binary.Comparation;
import ast.expressions.binary.Logic;
import ast.expressions.literal.LiteralCharacter;
import ast.expressions.literal.LiteralInteger;
import ast.expressions.literal.LiteralReal;
import ast.expressions.other.*;
import ast.statements.Assignment;
import ast.statements.Read;
import ast.visitor.AbstractVisitor;
import types.ErrorType;

public class VisitorLValue extends AbstractVisitor {

    @Override
    public Object visit(Aritmetic a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Comparation a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Logic a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(LiteralInteger a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(LiteralCharacter a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(LiteralReal a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(AccesoArray a, Object p) {
        super.visit(a, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Object visit(AccesoCampos a, Object p) {
        super.visit(a, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Object visit(Cast a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(MenosUnario a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Not a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Variable a, Object p) {
        super.visit(a, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Object visit(FunctionCall a, Object p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Assignment a, Object p) {
        super.visit(a,p);
        if(!a.getLeft().isLValue()){
            ErrorHandler.getInstance().addError(new ErrorType(a.getLeft().getLine(),a.getLeft().getColumn(),"No se le puede asignar un valor a esto"));
        }
        return null;
    }

    @Override
    public Object visit(Read a, Object p) {
        super.visit(a, p);
        if(!a.getList().get(0).isLValue()){
            ErrorHandler.getInstance().addError(new ErrorType(a.getList().get(0).getLine(),a.getList().get(0).getColumn(),"No se le puede asignar un valor a esto"));
        }
        return null;
    }
}

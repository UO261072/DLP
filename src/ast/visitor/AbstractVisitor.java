package ast.visitor;

import ast.ErrorHandler;
import ast.FunctionCall;
import ast.Program;
import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.expressions.binary.Aritmetic;
import ast.expressions.binary.Comparation;
import ast.expressions.binary.Logic;
import ast.expressions.literal.LiteralCharacter;
import ast.expressions.literal.LiteralInteger;
import ast.expressions.literal.LiteralReal;
import ast.expressions.other.*;
import ast.statements.*;
import com.sun.org.apache.xpath.internal.compiler.FunctionTable;
import types.ErrorType;
import types.Type;
import types.complex.FunctionType;

public abstract class AbstractVisitor implements Visitor {


    @Override
    public Object visit(FunDef a, Type p) {
        for(int i=0;i<((FunctionType)a.getType()).getParam().size();i++){
            ((FunctionType)a.getType()).getParam().get(i).accept(this,p);
        }
        for(int i=0;i<a.getSentences().size();i++){
            a.getSentences().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(VarDef a,Type p) {
        return null;
    }

    @Override
    public Object visit(Aritmetic a, Type p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Comparation a,Type p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }


    @Override
    public Object visit(Logic a, Type p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(LiteralInteger a, Type p) {
        return null;
    }

    @Override
    public Object visit(LiteralCharacter a, Type p) {
        return null;
    }

    @Override
    public Object visit(LiteralReal a, Type p) {
        return null;
    }

    @Override
    public Object visit(AccesoArray a,Type p) {
        a.getLocation().accept(this,p);
        a.getArray().accept(this,p);
        return null;
    }

    @Override
    public Object visit(AccesoCampos a, Type p) {
        a.getNombre().accept(this,p);
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Cast a,Type p) {
        a.getExpression().accept(this,p);
    return null;
    }

    @Override
    public Object visit(MenosUnario a,Type p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Not a, Type p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Variable a, Type p) {
        return null;
    }

    @Override
    public Object visit(Assignment a, Type p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(If a, Type p) {
        a.getCondition().accept(this,p);
        for(int i=0;i<a.getDoIf().size();i++){
            a.getDoIf().get(i).accept(this,p);
        }
        if(a.getDoElse()!=null) {
            for (int i = 0; i < a.getDoElse().size(); i++) {
                a.getDoElse().get(i).accept(this, p);
            }
        }
        return null;
    }

    @Override
    public Object visit(Read a, Type p) {
        if(a.getList()!=null) {
            for (int i = 0; i < a.getList().size(); i++) {
                a.getList().get(i).accept(this, p);
            }
        }
        else{
            ErrorHandler.getInstance().addError(new ErrorType(a.getLine(),a.getColumn(),"El objetivo de guardado del read no es valido o no existe"));
        }
        return null;
    }

    @Override
    public Object visit(Return a, Type p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(While a, Type p) {
        a.getCondition().accept(this,p);
        for(int i=0;i<a.getDoWhile().size();i++){
            a.getDoWhile().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(Write a, Type p) {
        for(int i=0;i<a.getList().size();i++){
            a.getList().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(FunctionCall a, Type p) {
        a.getFunction().accept(this,p);
        for(int i=0;i<a.getArgs().size();i++){
            a.getArgs().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(Program a, Type p) {
        for(int i=0;i<a.getDefinitions().size();i++){
            a.getDefinitions().get(i).accept(this,p);
        }
        return null;
    }
}

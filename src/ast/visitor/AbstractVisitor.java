package ast.visitor;

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

public abstract class AbstractVisitor implements Visitor {


    @Override
    public Object visit(FunDef a, Object p) {
        for(int i=0;i<a.getSentences().size();i++){
            a.getSentences().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(VarDef a, Object p) {
        return null;
    }

    @Override
    public Object visit(Aritmetic a, Object p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Comparation a, Object p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Logic a, Object p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(LiteralInteger a, Object p) {
        return null;
    }

    @Override
    public Object visit(LiteralCharacter a, Object p) {
        return null;
    }

    @Override
    public Object visit(LiteralReal a, Object p) {
        return null;
    }

    @Override
    public Object visit(AccesoArray a, Object p) {
        a.getLocation().accept(this,p);
        a.getArray().accept(this,p);
        return null;
    }

    @Override
    public Object visit(AccesoCampos a, Object p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Cast a, Object p) {
        a.getExpression().accept(this,p);
    return null;
    }

    @Override
    public Object visit(MenosUnario a, Object p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Not a, Object p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(Variable a, Object p) {
        return null;
    }

    @Override
    public Object visit(Assignment a, Object p) {
        a.getLeft().accept(this,p);
        a.getRight().accept(this,p);
        return null;
    }

    @Override
    public Object visit(If a, Object p) {
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
    public Object visit(Read a, Object p) {
        for(int i=0;i<a.getList().size();i++){
            a.getList().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(Return a, Object p) {
        a.getExpression().accept(this,p);
        return null;
    }

    @Override
    public Object visit(While a, Object p) {
        a.getCondition().accept(this,p);
        for(int i=0;i<a.getDoWhile().size();i++){
            a.getDoWhile().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(Write a, Object p) {
        for(int i=0;i<a.getList().size();i++){
            a.getList().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(FunctionCall a, Object p) {
        a.getFunction().accept(this,p);
        for(int i=0;i<a.getArgs().size();i++){
            a.getArgs().get(i).accept(this,p);
        }
        return null;
    }

    @Override
    public Object visit(Program a, Object p) {
        for(int i=0;i<a.getDefinitions().size();i++){
            a.getDefinitions().get(i).accept(this,p);
        }
        return null;
    }
}

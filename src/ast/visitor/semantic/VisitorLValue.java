package ast.visitor.semantic;

import ast.ErrorHandler;
import ast.FunctionCall;
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
import ast.visitor.AbstractVisitor;
import types.ErrorType;
import types.Type;
import types.complex.FunctionType;
import types.record.RecordField;
import types.record.RecordType;
import types.simple.Character;
import types.simple.Integer;
import types.simple.Real;
import types.simple.Void;

import java.lang.reflect.Field;

public class VisitorLValue extends AbstractVisitor {

    @Override
    public Object visit(FunDef a, Type p) {
        super.visit(a, ((FunctionType)a.getType()).getReturnType());
        return null;
    }

    @Override
    public Object visit(VarDef a, Type p) {
        if(a.getType() instanceof Void)
            a.setType(new ErrorType(a.getLine(),a.getColumn(),"No se pueden crear variables de tipo void"));
        if(a.getType() instanceof RecordType)
            a.getType().correctStruct(a);
        return super.visit(a, p);
    }

    @Override
    public Object visit(If a, Type p) {
        super.visit(a, p);
        if(!a.getCondition().getType().isLogical())
            new ErrorType(a.getLine(),a.getColumn(),"La condicion a de ser un entero");
        return null;
    }

    @Override
    public Object visit(Aritmetic a, Type p) {
        super.visit(a, p);
        a.setLValue(false);

        a.setType(a.getLeft().getType().arithmetic(a.getRight().getType(),a));
        return null;
    }



    @Override
    public Object visit(Comparation a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Logic a, Type p) {
        super.visit(a, p);
        a.setLValue(false);

        a.setType(a.getLeft().getType().logic(a.getRight().getType(),a));
        return null;
    }

    @Override
    public Object visit(LiteralInteger a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        a.setType(Integer.getInstance());
        return null;
    }

    @Override
    public Object visit(LiteralCharacter a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        a.setType(Character.getInstance());
        return null;
    }

    @Override
    public Object visit(LiteralReal a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        a.setType(Real.getInstance());
        return null;
    }

    @Override
    public Object visit(AccesoArray a, Type p) {
        super.visit(a, p);
        a.setLValue(true);
        a.setType(a.getArray().getType().indexing(a.getLocation().getType(),a));
        return null;
    }

    @Override
    public Object visit(AccesoCampos a, Type p) {
        super.visit(a, p);
        a.setLValue(true);
        if(a.getExpression() instanceof Variable){
            a.setType(a.getNombre().definition.getType().acceso(((Variable)a.getExpression()).getNombre(),a));
        }
        else {
            new ErrorType(a.getLine(), a.getColumn(), "No se puede acceder a un campo que no sea una variable");
        }
        return null;
    }

    @Override
    public Object visit(Cast a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Object visit(MenosUnario a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        a.setType(a.getExpression().getType().menosUnario(a));
        return null;
    }

    @Override
    public Object visit(Not a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        a.setType(a.getExpression().getType().negate(a));
        return null;
    }

    @Override
    public Object visit(Variable a, Type p) {
        super.visit(a, p);
        a.setLValue(true);
        if (p instanceof  RecordType){
            for(RecordField rf:((RecordType) p).getComponents()){
                a.setType(rf.getType());
            }
        }
        else {
            if (a.definition == null)
                a.setType(new ErrorType(a.getLine(), a.getColumn(), "La variable " + a.getNombre() + " no esta definida"));
            else
                a.setType(a.definition.getType());
        }
        return null;
    }

    @Override
    public Object visit(FunctionCall a, Type p) {
        super.visit(a, p);
        a.setLValue(false);
        if(a.getFunction().definition!=null)
            a.setType(a.getFunction().definition.getType().parentheisis(a.getArgs(),a));
        else
            a.setType(new ErrorType(a.getLine(),a.getColumn(),"La funcion no esta definida"));
        return null;
    }

    @Override
    public Object visit(Assignment a, Type p) {
        super.visit(a,p);
        if(a.getRight().getType() instanceof ErrorType||a.getLeft().getType() instanceof ErrorType)
            return null;
        if(!a.getLeft().isLValue()){
            new ErrorType(a.getLeft().getLine(),a.getLeft().getColumn(),"No se le puede asignar un valor a esto");
        }
        a.getLeft().getType().assgination(a.getRight().getType(),a);
        return null;
    }

    @Override
    public Object visit(Read a, Type p) {
        super.visit(a, p);
        if(a.getList()!=null) {
            if (!a.getList().get(0).isLValue()) {
                new ErrorType(a.getList().get(0).getLine(), a.getList().get(0).getColumn(), "No se le puede asignar un valor a esto");
            }
        }

        return null;
    }

    @Override
    public Object visit(Return a, Type p) {
        super.visit(a, p);
        if(a.getExpression().getType()!=p)
            new ErrorType(a.getLine(),a.getColumn(),"El tipo del return no es correcto");
        return null;
    }

    @Override
    public Object visit(While a, Type p) {
        super.visit(a, p);
        if(!a.getCondition().getType().isLogical())
            new ErrorType(a.getLine(),a.getColumn(),"La condicion a de ser un entero");
        return null;
    }

    @Override
    public Object visit(Write a, Type p) {
        super.visit(a, p);
        for (int i=0;i<a.getList().size();i++)
            if(     !(a.getList().get(i).getType() instanceof Integer)
                    ||!(a.getList().get(i).getType() instanceof Real)
                    ||!(a.getList().get(i).getType() instanceof Character))
                if (!(a.getList().get(i).getType() instanceof ErrorType))
                    new ErrorType(a.getLine(),a.getColumn(), "Solo se pueden escribir tipos basicos");
        return null;
    }
}

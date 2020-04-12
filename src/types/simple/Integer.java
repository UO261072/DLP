package types.simple;

import ast.ASTNode;
import ast.ErrorHandler;
import ast.visitor.Visitor;
import types.AbstractType;
import types.ErrorType;
import types.Type;

public class Integer extends AbstractType implements Type {

    private static Integer instance;

    public static Integer getInstance(){
        if(instance==null)
            instance=new Integer();
        return instance;
    }
    public Integer(){
        super();
    }
    @Override
    public boolean isLogical() {
        return true;
    }

    @Override
    public Type arithmetic(Type t, ASTNode node) {
        if(t instanceof ErrorType)
            return t;
        if(t==Integer.getInstance())
            return this;
        return super.arithmetic(t,node);
    }

    @Override
    public Type assgination(Type t, ASTNode node) {
        if(t instanceof ErrorType)
            return t;
        if(t==this)
            return this;
        return super.assgination(t, node);
    }

    @Override
    public Type logic(Type t, ASTNode node) {

        if(t instanceof ErrorType)
            return t;
        if(t instanceof Integer||t instanceof Real||t instanceof Character)
            return Integer.getInstance();


        return super.logic(t, node);
    }

    @Override
    public Type negate(ASTNode node) {
        return this;
    }

    @Override
    public Type comparation(Type t, ASTNode node) {
        if(t instanceof Integer||t instanceof Real)
            return Integer.getInstance();
        return super.comparation(t, node);
    }

    @Override
    public Type menosUnario(ASTNode node) {
        return this;
    }
}

package types.simple;

import ast.ASTNode;
import ast.visitor.Visitor;
import types.AbstractType;
import types.ErrorType;
import types.Type;

public class Real extends AbstractType implements Type {

    private static Real instance;

    public static Real getInstance(){
        if(instance==null)
            instance=new Real();
        return instance;
    }
    public Real(){
        super();
    }

    @Override
    public Type arithmetic(Type t, ASTNode node) {
        if(t instanceof ErrorType)
            return t;
        if(t==Real.getInstance())
            return this;
        return super.arithmetic(t, node);
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

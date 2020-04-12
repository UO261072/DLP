package types.simple;

import ast.ASTNode;
import types.AbstractType;
import types.ErrorType;
import types.Type;

public class Character extends AbstractType implements Type {


    private static Character instance;

    public static Character getInstance(){
        if(instance==null)
            instance=new Character();
        return instance;
    }
    public Character(){
        super();
    }

    @Override
    public Type assgination(Type t, ASTNode node) {
        if(t instanceof ErrorType)
            return t;
        if(t==this)
            return this;
        return super.assgination(t, node);
    }

}

package types;

import ast.AbstractASTNode;

public abstract class AbstractType implements Type {

    public AbstractType() {
        super();
    }

    public boolean isLogical(){
        return false;
    }
}

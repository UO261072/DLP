package types.simple;

import types.AbstractType;
import types.Type;

public class Integer extends AbstractType implements Type {

    @Override
    public boolean isLogical() {
        return true;
    }
}

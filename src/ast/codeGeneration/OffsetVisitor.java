package ast.codeGeneration;

import ast.definitions.VarDef;
import ast.visitor.AbstractVisitor;
import types.Type;

public class OffsetVisitor extends AbstractVisitor {
    int offset=0;

    @Override
    public Object visit(VarDef a, Type p) {
        super.visit(a, p);
        return null;
    }
}

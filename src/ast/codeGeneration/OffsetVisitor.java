package ast.codeGeneration;

import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.visitor.AbstractVisitor;
import types.Type;
import types.simple.Character;
import types.simple.Integer;
import types.simple.Real;

public class OffsetVisitor extends AbstractVisitor {
    int offset=0;
    int scope=0;
    int prevSize=0;

    @Override
    public Object visit(FunDef a, Type p) {
        scope++;
        prevSize=0;
        super.visit(a, p);
        scope--;
        return null;
    }

    @Override
    public Object visit(VarDef a, Type p) {
        super.visit(a, p);
        if (scope == 0){
            a.setOffset(prevSize);
            if(a.getType()== Integer.getInstance())
                prevSize+=2;
            if (a.getType()== Real.getInstance())
                prevSize+=4;
            if (a.getType()== Character.getInstance())
                prevSize+=1;
        }
        else if(scope>0){
            if(a.getType()== Integer.getInstance()) {
                prevSize += 2;
                a.setOffset(-prevSize);
            }
            if (a.getType()== Real.getInstance()) {
                prevSize += 4;
                a.setOffset(-prevSize);
            }
            if (a.getType()== Character.getInstance()) {
                prevSize += 1;
                a.setOffset(-prevSize);
            }
        }

        return null;
    }

}

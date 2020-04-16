package ast.codeGeneration;

import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.visitor.AbstractVisitor;
import types.Type;
import types.record.RecordField;
import types.record.RecordType;
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
            prevSize+=a.getType().size();
        }
        else if(scope>0){
            if(a.isParam()) {
                if(prevSize==0)
                    prevSize=4;


                a.setOffset(prevSize);
                prevSize+=a.getType().size();


            }else {
                if(prevSize>0)
                    prevSize=0;

                    prevSize-=a.getType().size();
                    a.setOffset(prevSize);

            }
        }
        if(a.getType() instanceof RecordType){
            int i=0;
            for (RecordField rf:((RecordType) a.getType()).getComponents()){
                rf.setOffset(i);
                i+=rf.getType().size();
                System.out.println(a.getName()+"."+rf.getName()+" "+rf.getOffset());
            }
        }
        System.out.println(a.getName()+" "+a.getOffset());
        return null;
    }

}

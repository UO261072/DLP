package ast.codeGeneration;

import ast.definitions.FunDef;
import ast.definitions.VarDef;
import ast.expressions.binary.Aritmetic;
import ast.expressions.binary.Comparation;
import ast.expressions.binary.Logic;
import ast.expressions.literal.LiteralCharacter;
import ast.expressions.literal.LiteralInteger;
import ast.expressions.literal.LiteralReal;
import ast.expressions.other.*;
import ast.statements.Assignment;
import ast.statements.Read;
import ast.statements.Write;
import types.Type;
import types.simple.Integer;

public class AddressCGVisitor  extends AbstractCGVisitor{

    private ValueCGVisitor valueCGVisitor;

    public AddressCGVisitor(CG cg,ValueCGVisitor valueCGVisitor) {
        super(cg);
        this.valueCGVisitor=valueCGVisitor;

    }

/*
    address[[Variable:expression->ID]]()=

    if(expression.definition.scope==0)
        <pusha> expression.definition.offset
    else
        <pushi> expression.definition.offset
        <push bp>
        <addi>


     */

    @Override
    public Object visit(Variable a, Type param) {
        if(a.definition.getScope()==0){
            cg.pusha(a.definition.getOffset());
        }else{
            cg.pushi(a.definition.getOffset());
            cg.pushbp();
            cg.add(Integer.getInstance());

        }
        return null;
    }

    /*
    address[[AccesoCampos:access -> Variable:var]]()=
    address[[var]]
     */

    @Override
    public Object visit(AccesoCampos a, Type param) {
        a.getNombre().accept(this,param);
        cg.pushi(a.getNombre().definition.getType().dirNum(a.getExpression()));
        cg.add(Integer.getInstance());
        return null;
    }

    /*address[[AccesoArray:acess -> Variable:var Expression:location]]
    value[[location]]
    <pushi> a.getType.size();
    <muli>
    address[[var]]
    <add>

     */

    @Override
    public Object visit(AccesoArray a, Type param) {
        a.getArray().accept(this,param);
        a.getLocation().accept(valueCGVisitor,param);
        cg.pushi(a.getType().size());
        cg.mul(Integer.getInstance());
        cg.add(Integer.getInstance());
        return null;
    }
}

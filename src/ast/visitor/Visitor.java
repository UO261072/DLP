package ast.visitor;

import ast.FunctionCall;
import ast.Program;
import ast.definitions.AbstractDefinition;
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

public interface Visitor {


    public Object visit(FunDef a, Object p);
    public Object visit(VarDef a, Object p);
    public Object visit(Aritmetic a,Object p);
    public Object visit(Comparation a, Object p);
    public Object visit(Logic a, Object p);
    public Object visit(LiteralInteger a, Object p);
    public Object visit(LiteralCharacter a, Object p);
    public Object visit(LiteralReal a, Object p);
    public Object visit(AccesoArray a, Object p);
    public Object visit(AccesoCampos a, Object p);
    public Object visit(Cast a, Object p);
    public Object visit(MenosUnario a, Object p);
    public Object visit(Not a, Object p);
    public Object visit(Variable a, Object p);
    public Object visit(Assignment a, Object p);
    public Object visit(If a, Object p);
    public Object visit(Read a, Object p);
    public Object visit(Return a, Object p);
    public Object visit(While a, Object p);
    public Object visit(Write a, Object p);
    public Object visit(FunctionCall a, Object p);
    public Object visit(Program a, Object p);
}

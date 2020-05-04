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
import types.Type;

public interface Visitor {


    public Object visit(FunDef a,Type param);
    public Object visit(VarDef a, Type param);
    public Object visit(Aritmetic a,Type param);
    public Object visit(Comparation a, Type param);
    public Object visit(Logic a, Type param);
    public Object visit(LiteralInteger a,Type param);
    public Object visit(LiteralCharacter a, Type param);
    public Object visit(LiteralReal a, Type param);
    public Object visit(AccesoArray a, Type param);
    public Object visit(AccesoCampos a, Type param);
    public Object visit(Cast a, Type param);
    public Object visit(MenosUnario a, Type param);
    public Object visit(Not a,Type param);
    public Object visit(Variable a, Type param);
    public Object visit(Assignment a, Type param);
    public Object visit(If a, Type param);
    public Object visit(Read a, Type param);
    public Object visit(Return a, Type param);
    public Object visit(While a,Type param);
    public Object visit(Write a, Type param);
    public Object visit(FunctionCall a, Type param);
    public Object visit(Program a, Type param);
    public Object visit(ArrayCharAssignment a, Type param);
}

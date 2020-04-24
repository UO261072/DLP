package ast.codeGeneration;

import ast.FunctionCall;
import ast.Program;
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
import ast.visitor.Visitor;
import jdk.nashorn.internal.codegen.CompilerConstants;
import types.Type;

public class AbstractCGVisitor implements Visitor {

    protected CG cg;

    public AbstractCGVisitor(CG cg){
        this.cg=cg;
    }

    public CG getCg() {
        return cg;
    }

    @Override
    public Object visit(FunDef a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(VarDef a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Aritmetic a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Comparation a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Logic a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(LiteralInteger a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(LiteralCharacter a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(LiteralReal a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(AccesoArray a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(AccesoCampos a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Cast a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(MenosUnario a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Not a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Variable a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Assignment a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(If a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Read a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Return a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(While a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Write a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(FunctionCall a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }

    @Override
    public Object visit(Program a, Type param) {
        throw new IllegalStateException("Code generation template \""+a.getClass()+"\" not defined for the code function" + this.getClass().getName());
    }




}

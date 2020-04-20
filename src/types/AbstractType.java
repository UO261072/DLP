package types;

import ast.ASTNode;
import ast.AbstractASTNode;
import ast.ErrorHandler;
import ast.expressions.Expression;
import parser.CmmParser;

import java.util.List;

public abstract class AbstractType implements Type {

    public AbstractType() {
        super();
    }

    public boolean isLogical(){
        return false;
    }

    public Type arithmetic(Type t, ASTNode node){
        return new ErrorType(node.getLine(),node.getColumn(),"No se permite esta operacion aritmetica");
    }

    public Type indexing(Type t,ASTNode node){
        return new ErrorType(node.getLine(),node.getColumn(),"No se puede aplicar el acceso a Array a este tipo");
    }

    @Override
    public String suffix() {
        throw new IllegalArgumentException();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Type parentheisis(List<Expression> t, ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"No se puede invocar una funcion de este tipo");
    }

    @Override
    public Type menosUnario(ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"No se puede transformar en negativo este tipo");
    }

    @Override
    public Type assgination(Type t, ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"El tipo a asignar no coencide con el de la variable");
    }

    @Override
    public Type correctStruct(ASTNode node) {
        return null;
    }

    @Override
    public Type acceso(String name,ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"Este tipo no tiene campos a los que acceder");
    }

    @Override
    public Type logic(Type t, ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"Este tipo no permite operaciones logicas");
    }

    @Override
    public Type negate(ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"No se puede negar este tipo");
    }

    @Override
    public Type comparation(Type t, ASTNode node) {
        return new ErrorType(node.getLine(),node.getColumn(),"No se pueden comparar estos tipos");
    }
}

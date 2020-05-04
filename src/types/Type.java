package types;

import ast.ASTNode;
import ast.expressions.Expression;

import java.util.List;

public interface Type  {

    int size();

    boolean isLogical();

    String suffix();

    Type arithmetic(Type t,ASTNode node);

    Type indexing(Type t, ASTNode node);

    Type parentheisis(List<Expression> t, ASTNode node);

    Type menosUnario(ASTNode node);

    Type assgination(Type t,ASTNode node);

    Type correctStruct(ASTNode node);

    Type acceso(String name,ASTNode node);

    Type logic(Type t,ASTNode ast);

    Type negate(ASTNode node);

    Type comparation(Type t,ASTNode node);

    Type arrayCharAssignation(String array,ASTNode node);

    Type subsegment(Type t,ASTNode node);

    int dirNum(Expression e);
}

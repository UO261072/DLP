package types;

import ast.ASTNode;
import ast.expressions.Expression;

import java.util.List;

public interface Type  {

    boolean isLogical();

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
}

grammar Cmm;	
@header{
    import ast.*;
    import ast.definitions.*;
    import types.*;
    import types.complex.*;
    import types.complex.FunctionType;
    import types.simple.*;
    import types.record.*;
    import types.complex.FunctionType;
    import ast.statements.*;
    import ast.expressions.Expression;
    import ast.expressions.literal.*;
    import ast.expressions.other.*;
    import ast.expressions.binary.*;
}
       program returns [Program ast=new Program(0,0,new ArrayList<AbstractDefinition>());]: (
       fundef {$ast.addDefinition($fundef.ast);}
       |(vardef {$ast.addDefinition($vardef.ast);
       $ast.addDefinitions($vardef.astl);}';')|
       struct{$ast.addDefinition($struct.ast);})*;

       statement returns [ast.statements.Statement ast]: (asignation{$ast=$asignation.ast;}|(funinv{$ast=$funinv.ast;}';')|devuelve{$ast=$devuelve.ast;}|struct{$ast=$struct.ast;}|array{$ast=$array.ast;}|write{$ast=$write.ast;}|read{$ast=$read.ast;}|mientras{$ast=$mientras.ast;}|si{$ast=$si.ast;})

       ;


       vardef returns [VarDef ast,List<AbstractDefinition> astl=new ArrayList<AbstractDefinition>();]: primitiveType id1=ID (',' id2=ID{$astl.add(new VarDef($id2.getLine(),$id2.getCharPositionInLine(),$id2.text,$primitiveType.ast));})*
       {$ast=new VarDef($id1.getLine(),$id1.getCharPositionInLine(),$id1.text,$primitiveType.ast);};



       expr returns[Expression ast]: ID {$ast=new Variable($ID.getLine(),$ID.getCharPositionInLine(),$ID.text);}
       |REAL_CONSTANT {$ast= new LiteralReal($REAL_CONSTANT.getLine(),$REAL_CONSTANT.getCharPositionInLine(),REAL_CONSTANT);}
       |INT_CONSTANT {$ast= new LiteralInteger($INT_CONSTANT.getLine(),$INT_CONSTANT.getCharPositionInLine(),INT_CONSTANT);}
       |CHAR_CONSTANT {$ast= new LiteralCharacter($CHAR_CONSTANT.getLine(),$CHAR_CONSTANT.getCharPositionInLine(),(char)CHAR_CONSTANT);}
       |'('primitiveType')'expr {$ast= new Cast(0,0,$expr.ast,$primitiveType.ast);}
       |e1=expr '['e2=expr']' {$ast=new AccesoArray(0,0,$e1.ast,$e2.ast);}
       |ID '.' e2=expr  {$ast=new AccesoCampos(0,0,$ID.text,$e2.ast);}
       |'-' expr {$ast= new MenosUnario(0,0,$expr.ast);}
       |e1=expr s=('/'|'*'|'%') e2=expr{$ast= new Aritmetic(0,0,$s.text,$e1.ast,$e2.ast);}
       |e1=expr s=('+'|'-') e2=expr{$ast= new Aritmetic(0,0,$s.text,$e1.ast,$e2.ast);}
       |e1=expr s=('<'|'>='|'=='|'!='|'<='|'>') e2=expr {$ast= new Comparation(0,0,$s.text,$e1.ast,$e2.ast);}
       | '!'expr {$ast= new Not(0,0,$expr.ast);}
       | e1=expr s=('||'|'&&') e2=expr {$ast= new Logic(0,0,$s.text,$e1.ast,$e2.ast);}
       ;


       primitiveType returns[Type ast]: 'int'{$ast=new types.simple.Integer();}
       |'char'{$ast=new types.simple.Character();}
       |'double'{$ast=new Real();}
       |'void'{$ast=new types.simple.Void();};



       fundef returns [FunDef ast]: primitiveType ID '(' param ')''{'funbody?'}'
       {
       Type fun=new FunctionType($primitiveType.ast,$param.ast);
       $ast=new FunDef($ID.getLine(),$ID.getCharPositionInLine(),$ID.text,fun,$funbody.ast);
       }
       ;
       funbody returns [List<ast.statements.Statement> ast=new ArrayList<Statement>();]: ((vardef{
       $ast.add($vardef.ast);
       for(int i=0;i<$vardef.astl.size();i++){
            $ast.add((VarDef)$vardef.astl.get(i));
       }
       }';')
       |statement{$ast.add($statement.ast);})*
       ;

       param returns [List<VarDef> ast=new ArrayList<VarDef>();]: (|vardef{$ast.add($vardef.ast);} (',' vardef{$ast.add($vardef.ast);})*)

       ;

       funinv returns[FunctionCall ast]: ID '('args')'{$ast=new FunctionCall(0,0,new Variable(0,0,$ID.text),$args.ast);};

       args returns[List<Expression> ast=new ArrayList<Expression>()]: (|e1=expr{$ast.add($e1.ast);} (',' e2=expr{$ast.add($e2.ast);})*);

       devuelve returns[Return ast]: 'return' expr {$ast=new Return(0,0,$expr.ast);}';';

        array returns[VarDef ast]: arrayType ID{$ast=new VarDef(0,0,$ID.text,$arrayType.ast);}';';


        arrayType returns[ArrayType ast]:{List<java.lang.Integer> l=new ArrayList<java.lang.Integer>();} primitiveType ('['INT_CONSTANT{l.add(java.lang.Integer.parseInt($INT_CONSTANT.text));}']')*{
        ArrayType a=new ArrayType(java.lang.Integer.parseInt($INT_CONSTANT.text),$primitiveType.ast);
        for(int i=l.size()-2;i>=0;i--){
            ArrayType b=new ArrayType(l.get(i),a);
            a=b;
        }
        $ast=a;
        };

       asignation returns[Assignment ast]: e1=expr '=' e2=expr {$ast=new Assignment(0,0,$e1.ast,$e2.ast);}';';

       struct returns[VarDef ast]: 'struct' '{' structComponents'}' ID ';'{
       RecordType rt=new RecordType($structComponents.ast);
       $ast=new VarDef($ID.getLine(),$ID.getCharPositionInLine(),$ID.text,rt);};

       structComponents returns[List<RecordField> ast=new ArrayList<RecordField>()]: (vardef{$ast.add(new RecordField($vardef.ast.getName(),$vardef.ast.getType(),0));}';')* ;







       mientras returns[While ast]:'while' '('expr')'('{'funbody{$ast=new While(0,0,$expr.ast,$funbody.ast);}'}'|statement{
       List<ast.statements.Statement> l=new ArrayList<Statement>();
       if($statement.ast!=null)
       l.add($statement.ast);
       $ast=new While(0,0,$expr.ast,l );});

       si returns[If ast]:'if''('expr')'(('{'funbody{$ast=new If(0,0,$expr.ast,$funbody.ast,null);}'}'|statement{
        List<ast.statements.Statement> l=new ArrayList<Statement>();
        l.add($statement.ast);
        $ast=new If(0,0,$expr.ast,l,null);}) si_no?{$ast.setDoElse($si_no.ast);});

       si_no returns[List<ast.statements.Statement> ast]:'else'('{'funbody{$ast=new ArrayList<Statement>();$ast.addAll($funbody.ast);}'}'|statement{
       List<ast.statements.Statement> l=new ArrayList<Statement>();
       l.add($statement.ast);
       $ast=l;});
       /*si_no returns[List<ast.statements.Statement>]:'else'('{'funbody{$ast=new If(0,0,null,$funbody.ast,null);}'}'|statement{
       List<ast.statements.Statement> l=new ArrayList<Statement>();
       l.add($statement.ast);
       $ast=new If(0,0,null,l,null);});*/


       write returns[Write ast]: 'write' (listaChar{$ast=new Write(0,0,$listaChar.ast);}|funinv{List<Expression> a=new ArrayList<Expression>();a.add($funinv.ast);$ast=new Write(0,0,a);}) ';';

       read returns[Read ast]: 'read' (listaChar{$ast=new Read(0,0,$listaChar.ast);}|funinv{$ast=new Read(0,0,null);}) ';';

       listaChar returns[List<Expression> ast=new ArrayList<Expression>()]: c1=CHAR_CONSTANT{$ast.add(new LiteralCharacter($c1.getLine(),$c1.getCharPositionInLine(),(char)$c1.text.charAt(0)));} (','c2=CHAR_CONSTANT{$ast.add(new LiteralCharacter($c2.getLine(),$c2.getCharPositionInLine(),(char)$c2.text.charAt(0)));})*;




WS:[ \r\t\n] -> skip
;

COMENTARIOS: (('//'.*?'\n')|('//'.*?EOF)|('/*'.*?'*/')|('/*'.*?EOF)) ->skip
;

ID: ([a-zA-Z]|'_')[a-zA-Z0-9_]*
;
REAL_CONSTANT: ([0-9]*'.'[0-9]+)|
                ([0-9]+'.'[0-9]*)|
                ([0-9]*'.'[0-9]+('e'|'E')'-'*[1-9][0-9]*)|
                ([1-9][0-9]*('e'|'E')'-'*[1-9][0-9]*)
;
INT_CONSTANT: [1-9][0-9]*|'0'
            ;
CHAR_CONSTANT: '\''.'\''|
                '\'''\\'([1-9][0-9]*|'0')'\''|
                '\'''\\''n''\''|'\'''\\''t''\''
;


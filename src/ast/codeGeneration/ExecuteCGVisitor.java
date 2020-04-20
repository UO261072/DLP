package ast.codeGeneration;

public class ExecuteCGVisitor extends AbstractCGVisitor{

    private AddressCGVisitor addressCGVisitor;
    private ValueCGVisitor valueCGVisitor;

    public ExecuteCGVisitor(CG cg) {
        super(cg);
        valueCGVisitor=new ValueCGVisitor(cg);
        addressCGVisitor=new AddressCGVisitor(cg);
    }


    /*
    execute [[Program :program -> definition*]]() =
    for(Definition d:definition*)
        if(d instanceof VarDef)
            execute[[d]]
    <call main>
    <halt>

    for(Definition d:definition*)
        if(d instanceof FunDef)
            execute[[d]]


    visit (Program p){
        for(Definition d:definition*)
            if(d instanceof VarDef)
                d.accept(this,p)
        CG.call("main");
        CG.halt();

        for(Definition d:definition*)
            if(d instanceof FunDef)
                d.accept(this,p)
    }

     */

    /*
    execute[[Write : statement -> expression*]]()=
    for(Expression exp:expression*)
        value[[exp]]
        <out> exp.type.suffix()
     */

    /*
    execute[[Read:statement -> variable]]()=
    addressCGVisitor.visit(variable)
    <load> variable.definition.getType().suffix()
     */

    /*
    execute[[FunDef:fundef -> type statement*]]()=
    fundef.name <:>
    //comentamos parametros
    //Comentamos variable locales
    <enter> fundef.sumbyteslocalvar
    for(Sentencia s:sentencias)
        execute[[s]]

    if(fundef.returnType==Void)
    <ret 0,fundef.sumbyteslocalvar>
     */

}

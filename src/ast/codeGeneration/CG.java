package ast.codeGeneration;

import java.io.PrintWriter;

public class CG {

    private PrintWriter out;

    public CG(String outputFilename, String sourceFileName){
        try{
            this.out=new PrintWriter(outputFilename);
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

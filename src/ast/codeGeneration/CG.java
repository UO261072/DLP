package ast.codeGeneration;

import types.Type;
import types.simple.Integer;

import java.io.IOException;
import java.io.PrintWriter;

public class CG {

    private PrintWriter out;

    public CG(String outputFilename, String sourceFileName){
        try{
            this.out=new PrintWriter(outputFilename);
        }catch (IOException e){
            System.err.println("Error opening the file");
            System.exit(-1);
        }
        this.source(sourceFileName);
    }

    private void source(String file){
        out.println("#source \"" +file+"\"");
        out.println();
        out.flush();
    }

    public void call(String name) {
        out.println("call "+name);
        out.flush();
    }

    public void halt() {
        out.println("halt");
        out.flush();
    }

    public void var(String type, String name, int offset) {
        out.println("\t. * "+type+" "+name+" (offset "+offset+")");
        out.flush();
    }

    public void anotation(String anotation){
        out.println(anotation);
        out.flush();
    }

    public void fun(String name) {
        out.println(" "+name+":");
        out.flush();
    }

    public void parameters() {
        out.println("\t' * Parameters");
        out.flush();
    }

    public void local() {
        out.println("\t' * Local variables");
        out.flush();
    }

    public void enter(int i) {
        out.println("\tenter "+i);
        out.flush();
    }

    public void ret(int i, int j) {
        out.println("\tret "+i+","+j+","+0);
        out.flush();
    }

    public void store(Type type) {
        out.println("\tstore"+type.suffix());
        out.flush();
    }

    public void pusha(int add) {
        out.println("\tpusha "+ add);
        out.flush();
    }

    public void pushi(int i) {
        out.println("pushi "+i);
        out.flush();
    }


    public void pushbp() {
    }

    public void add(Type type) {
        out.println("add"+type.suffix());
        out.flush();
    }
}

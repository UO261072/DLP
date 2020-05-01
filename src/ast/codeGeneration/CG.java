package ast.codeGeneration;

import ast.expressions.Expression;
import ast.expressions.other.Variable;
import types.Type;
import types.complex.ArrayType;
import types.simple.Character;
import types.simple.Integer;
import types.simple.Real;

import java.io.IOException;
import java.io.PrintWriter;

public class CG {

    private PrintWriter out;
    private int numberOfLabels;

    public CG(String outputFilename, String sourceFileName){
        numberOfLabels=0;
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

    public void var(Type type, String name, int offset) {
        out.println("\t' * "+type+" "+name+" (offset "+offset+")");
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

    public void ret(int i, int j,int k) {
        out.println("\tret "+i+","+j+","+k);
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
        out.println("\tpushi "+i);
        out.flush();
    }


    public void pushbp() {
        out.println("\tpush bp");
        out.flush();
    }

    public void add(Type type) {
        out.println("\tadd"+type.suffix());
        out.flush();
    }

    public void push(Object value) {
        if(value instanceof java.lang.Character){
            out.println("\tpush"+Character.getInstance().suffix()+" "+ (int)((char)value));
        }else if(value instanceof java.lang.Integer){
            out.println("\tpush"+Integer.getInstance().suffix()+" "+value);
        }else{
            out.println("\tpush"+ Real.getInstance().suffix()+" "+value);
        }
        out.flush();
    }

    public void out(Expression e) {
        out.println("\tout"+e.getType().suffix());
        out.flush();
    }

    public void load(Type a) {
        out.println("\tload"+a.suffix());
        out.flush();
    }

    public void sub(Type type) {
        out.println("\tsub"+type.suffix());
        out.flush();
    }

    public void mul(Type type) {
        out.println("\tmul"+type.suffix());
        out.flush();
    }

    public void div(Type type) {
        out.println("\tdiv"+type.suffix());
        out.flush();
    }
    public void mod(Type type) {
        out.println("\tmod"+type.suffix());
        out.flush();
    }

    public void convert(Type type, Type type1) {
        if((type==Character.getInstance()&&type1==Real.getInstance())||(type1==Character.getInstance()&&type==Real.getInstance())){
            out.println("\t"+type1.suffix()+"2"+Integer.getInstance().suffix());
            out.flush();
            out.println("\t"+Integer.getInstance().suffix()+"2"+type.suffix());
            out.flush();
        }
        else{
            out.println("\t"+type1.suffix()+"2"+type.suffix());
            out.flush();
        }

    }

    public void in(Type type) {
        out.println("\tin"+type.suffix());
        out.flush();
    }

    public void gt(Type type) {
        out.println("\tgt"+type.suffix());
        out.flush();
    }

    public void lt(Type type) {
        out.println("\tlt"+type.suffix());
        out.flush();
    }

    public void ge(Type type) {
        out.println("\tge"+type.suffix());
        out.flush();
    }

    public void le(Type type) {
        out.println("\tle"+type.suffix());
        out.flush();
    }

    public void eq(Type type) {
        out.println("\teq"+type.suffix());
        out.flush();
    }

    public void ne(Type type) {
        out.println("\tne"+type.suffix());
        out.flush();
    }

    public void and() {
        out.println("\tand");
        out.flush();
    }

    public void or() {
        out.println("\tor");
        out.flush();
    }

    public void not() {
        out.println("\tnot");
        out.flush();
    }

    public void pop(Type type) {
        out.println("\tpop"+type.suffix());
        out.flush();
    }

    public int getLabel(int i) {
        int a=numberOfLabels;
        numberOfLabels+=i;
        return a;
    }

    public void label(int labelNumber) {
        out.println(" label"+labelNumber+":");
        out.flush();
    }

    public void jz(int labelNumber) {
        out.println("\tjz\tlabel"+labelNumber);
        out.flush();
    }

    public void jmp(int labelNumber) {
        out.println("\tjmp\tlabel"+labelNumber);
        out.flush();
    }


    public void line(int line) {
        this.anotation("\n#line\t"+line+"\n");
    }


}

import ast.Program;
import ast.codeGeneration.OffsetVisitor;
import ast.visitor.Visitor;
import ast.visitor.identification.VisitorIdentification;
import ast.visitor.semantic.VisitorLValue;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import parser.*;
import ast.*;

import org.antlr.v4.runtime.*;
import types.ErrorType;

public class Main {
	
	public static void main(String... args) throws Exception {
		   if (args.length<1) {
		        System.err.println("Please, pass me the input file.");
		        return;
		    }
		   		 			
		 // create a lexer that feeds off of input CharStream
		CharStream input = CharStreams.fromFileName(args[0]);
		CmmLexer lexer = new CmmLexer(input);

		// create a parser that feeds off the tokens buffer
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		CmmParser parser = new CmmParser(tokens);	
		Program p=parser.program().ast;
		System.out.println("FINISHED");
		Visitor v1=new VisitorIdentification();
		Visitor v=new VisitorLValue();
		Visitor v2=new OffsetVisitor();
		v1.visit(p,null);
		v.visit(p,null);
		//v2.visit(p,null);
		if(ErrorHandler.getInstance().anyError()){
			ErrorHandler.getInstance().showErrors();
		}else {
			IntrospectorModel model = new IntrospectorModel("Program", p);
			new IntrospectorTree("Tree", model);
			v2.visit(p,null);
		}
	}
	

}

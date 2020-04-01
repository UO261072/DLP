package ast;

import types.ErrorType;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    private static ErrorHandler instance = null;
    List<ErrorType> errors;

    private ErrorHandler(){
        errors=new ArrayList<ErrorType>();
    }

    public static ErrorHandler getInstance(){
        if(instance==null)
            instance=new ErrorHandler();
        return instance;
    }

    public void addError(ErrorType e){
        this.errors.add(e);
    }

    public void showErrors(){
        for(ErrorType e:errors){
            System.out.println(e);
        }

    }
    public boolean anyError(){
        return this.errors.size()!=0;
    }
}

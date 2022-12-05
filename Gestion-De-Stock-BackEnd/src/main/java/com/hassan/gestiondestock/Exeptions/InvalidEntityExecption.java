package com.hassan.gestiondestock.Exeptions;

import lombok.Getter;

import java.util.List;

public class InvalidEntityExecption extends RuntimeException{

    //une entity n'est pas valid
    @Getter
    private ErrorsCodes errorsCodes;

    @Getter
    private List<String> errors;

    public InvalidEntityExecption(String message){
        super(message);
    }
    public InvalidEntityExecption(String message,Throwable cause){
        super(message,cause);
    }
    public InvalidEntityExecption(String message,Throwable cause,ErrorsCodes errorCode){
        super(message,cause);
        this.errorsCodes=errorCode;
    }
    public InvalidEntityExecption(String message,ErrorsCodes errorCode){
        super(message);
        this.errorsCodes=errorCode;
    }
    public InvalidEntityExecption(String message,ErrorsCodes errorCode,List<String> errors){
        super(message);
        this.errorsCodes=errorCode;
        this.errors=errors;
    }

}

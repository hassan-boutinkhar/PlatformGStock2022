package com.hassan.gestiondestock.Exeptions;

import lombok.Getter;

import java.util.ArrayList;

public class EntityNotFoundException extends RuntimeException{

    // un entity qui n'existe pas ou on cherche dans la base de donnes une chose qui n'existent pas
    @Getter
    private ErrorsCodes errorsCodes;

    public EntityNotFoundException(String message){
        super(message);
    }
    public EntityNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public EntityNotFoundException(String message,Throwable cause,ErrorsCodes errorCode){
        super(message,cause);
        this.errorsCodes=errorCode;
    }
    public EntityNotFoundException(String message,ErrorsCodes errorCode){
        super(message);
        this.errorsCodes=errorCode;
    }
}

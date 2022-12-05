package com.hassan.gestiondestock.Handelrs;

import com.hassan.gestiondestock.Exeptions.ErrorsCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsDto {

    private Integer httpCode;

    private ErrorsCodes code;

    private String message;

    private List<String> errors =new ArrayList<>();

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public ErrorsCodes getCode() {
        return code;
    }

    public void setCode(ErrorsCodes code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public static class Builder {};

}

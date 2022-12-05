package com.hassan.gestiondestock.Handelrs;

import com.hassan.gestiondestock.Exeptions.EntityNotFoundException;
import com.hassan.gestiondestock.Exeptions.InvalidEntityExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExecptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorsDto> handlerException(EntityNotFoundException exeception, WebRequest webRequest){

        final HttpStatus notFound =HttpStatus.NOT_FOUND;
        final ErrorsDto errorsDto = ErrorsDto.builder()
                .code(exeception.getErrorsCodes())
                .httpCode(notFound.value())
                .message(exeception.getMessage())
                .build();
        return new ResponseEntity<>(errorsDto,notFound);
    }

    @ExceptionHandler(InvalidEntityExecption.class)
    public ResponseEntity<ErrorsDto> handlerException(InvalidEntityExecption exeception, WebRequest webRequest){

        final HttpStatus badRequest =HttpStatus.BAD_REQUEST;
        final ErrorsDto errorsDto = ErrorsDto.builder()
                .code(exeception.getErrorsCodes())
                .httpCode(badRequest.value())
                .message(exeception.getMessage())
                .errors(exeception.getErrors())
                .build();
        return new ResponseEntity<>(errorsDto,badRequest);
    }

}

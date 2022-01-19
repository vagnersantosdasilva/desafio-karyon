package br.com.bagarote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){
        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("Recurso não encontrado");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<?> handlerException(BusinessRuleException ex){

        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("Erro de regra de negócio");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerException(AccessDeniedException ex){

        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("Erro");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception ex){

        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("Erro");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }


}

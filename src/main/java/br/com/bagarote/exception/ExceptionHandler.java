package br.com.bagarote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){
        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("O recurso não foi encontrado");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception ex){

        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("Erro interno");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }
}

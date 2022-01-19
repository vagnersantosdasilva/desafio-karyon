package br.com.bagarote.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class MessageError {

    public  static void messageError (BindingResult result) throws Exception {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError oe :result.getAllErrors() ){
                sb.append(oe.getDefaultMessage().toString()+" | ");
            }
            throw new Exception(sb.toString());
        }
    }
}

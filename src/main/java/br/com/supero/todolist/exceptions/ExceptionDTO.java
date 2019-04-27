package br.com.supero.todolist.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ExceptionDTO {
	 
    private HttpStatus status;
    private String message;
    private List<String> errors;
 
    public ExceptionDTO(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    public ExceptionDTO(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
    
    public HttpStatus getStatus(){
    	return this.status;
    }
}
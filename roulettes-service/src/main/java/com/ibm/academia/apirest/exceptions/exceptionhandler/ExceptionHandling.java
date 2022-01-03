package com.ibm.academia.apirest.exceptions.exceptionhandler;

import com.ibm.academia.apirest.exceptions.exceptionresponse.ExceptionResponse;
import com.ibm.academia.apirest.exceptions.exeptiontypes.BadRequestException;
import com.ibm.academia.apirest.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.apirest.exceptions.exeptiontypes.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> invalidFormatException(BadRequestException exception, WebRequest request)
    {
        return createExceptionResponse(exception,request);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException exception, WebRequest request)
    {
        return createExceptionResponse(exception,request);
    }

    private ResponseEntity<Object>  createExceptionResponse(ExceptionType exception, WebRequest request){
        ExceptionResponse response=new ExceptionResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(exception.getStatus().value());
        response.setError(exception.getStatus());
        response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(response, response.getError());
    }
}

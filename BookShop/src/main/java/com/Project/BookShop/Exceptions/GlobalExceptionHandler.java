package com.Project.BookShop.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleRuntimeException(RuntimeException e){
        ErrorObject responseObject = new ErrorObject();
        responseObject.setMessage(e.getMessage());
        responseObject.setCode("C500");
        return ResponseEntity.status(500).body(responseObject);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleRuntimeException(RecordNotFound e){
        ErrorObject responseObject = new ErrorObject();
        responseObject.setMessage("Record not found");
        responseObject.setCode("C404");
        return ResponseEntity.status(500).body(responseObject);
    }
}

package com.antonio.gestao_vagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<List<ErrorMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      List<ErrorMessage> errors = new ArrayList<>();
      ex.getBindingResult().getFieldErrors().forEach(error -> {
         String message = error.getDefaultMessage();
         ErrorMessage errorMessage = new ErrorMessage(message, error.getField());
         errors.add(errorMessage);
      });
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(UserFoundException.class)
   public ResponseEntity<String> handleUserFoundException(UserFoundException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }
}

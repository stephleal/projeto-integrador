package com.w4.projetoIntegrador.advice;

import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.exceptions.CartCannotBeCancelException;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.exceptions.UserDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PersistenceExceptionAdvice {

    private static Logger log = LoggerFactory.getLogger(PersistenceExceptionAdvice.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointer(NullPointerException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(bodyOfResponse);
    }

    @ExceptionHandler(value = ArithmeticException.class)
    protected ResponseEntity<Object> erroCalculo(ArithmeticException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.internalServerError().body(bodyOfResponse);
    }

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> businessException(BusinessException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> notFoundException(NotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = UserDoesNotExistException.class)
    protected ResponseEntity<Object> userDoesNotExistException(UserDoesNotExistException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(bodyOfResponse);
    }

    @ExceptionHandler(value = CartCannotBeCancelException.class)
    protected ResponseEntity<Object> cartCannotBeCancelException(CartCannotBeCancelException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(422).body(bodyOfResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
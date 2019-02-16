package com.practice.demo.exception;

import com.practice.demo.exception.ErrorDetails;
import com.practice.demo.exception.bookNotFound.BookNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@ControllerAdvice
public class EntityNotFoundAdvice {

    @Autowired
    private ErrorAttributes errorAttributes;

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<?> bookNotFoundHandler(BookNotFoundException e,HttpServletRequest  request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = new ErrorDetails(new Date(), httpStatus.value(), httpStatus.name(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(errorDetails);
    }

}

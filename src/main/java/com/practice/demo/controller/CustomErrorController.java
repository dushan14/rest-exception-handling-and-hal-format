package com.practice.demo.controller;

import com.practice.demo.exception.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    public ResponseEntity<?> error(Exception e,WebRequest request, HttpServletResponse response){
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(request, false);
        String path = (String) errorAttributes.get("path");
        HttpStatus httpStatus = HttpStatus.valueOf((Integer) errorAttributes.get("status"));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), httpStatus.value(), httpStatus.name(), e.getMessage(), path);
        return ResponseEntity.status(httpStatus).body(errorDetails);
    }
}

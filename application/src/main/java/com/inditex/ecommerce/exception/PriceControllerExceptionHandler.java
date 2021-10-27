package com.inditex.ecommerce.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeansException;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceControllerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    ResponseEntity badRequestCustomMessage(IllegalArgumentException e1, ConversionException e2, BeansException e3, MethodArgumentTypeMismatchException e4, ConversionFailedException e5){
        Map<String,String> response = new HashMap<>();
        response.put("Status", "Bad Request");
        response.put("Message", "Invalid applicationDate. Format must be [yyyy-MM-ddThh:mm], or [yyyy-MM-ddThh:mm:ss]");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler
    @ResponseBody
    ResponseEntity sqlExceptionErrorCustomMessage(SQLException e){
        Map<String,String> response = new HashMap<>();
        response.put("Status", "Internal Server Error");
        response.put("message", "An Error has occurred connecting to the database");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}

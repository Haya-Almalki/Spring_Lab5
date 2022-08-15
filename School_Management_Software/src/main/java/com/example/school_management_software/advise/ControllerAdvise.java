package com.example.school_management_software.advise;
import com.example.school_management_software.exceptions.ApiException;
import com.example.school_management_software.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
public class ControllerAdvise {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity apiException(ApiException apiException){
        String message=apiException.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message,400));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("SERVER ERROR!",500));
    }
}

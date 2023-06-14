package com.espritnoir.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CafeUtils {
    private CafeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return  new ResponseEntity<String>("{\"Message\":\""+responseMessage+"\"}", httpStatus);
    }
}

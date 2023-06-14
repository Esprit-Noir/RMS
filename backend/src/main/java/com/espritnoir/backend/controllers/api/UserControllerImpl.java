package com.espritnoir.backend.controllers.api;

import com.espritnoir.backend.Services.UserService;
import com.espritnoir.backend.contants.CafeConstants;
import com.espritnoir.backend.controllers.UserController;
import com.espritnoir.backend.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
       try{
            return userService.signup(requestMap);
       }catch (Exception exception){
           exception.printStackTrace();
       }
       return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

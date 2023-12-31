package com.espritnoir.backend.Services.serviceImpl;

import com.espritnoir.backend.Services.UserService;
import com.espritnoir.backend.contants.CafeConstants;
import com.espritnoir.backend.models.User;
import com.espritnoir.backend.repositories.UserRepository;
import com.espritnoir.backend.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    // Register User
    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
       log.info("Inside signup {}", requestMap);
       try {
           if (validateSignUpMap(requestMap)) {
               User user = userRepository.findByEmail(requestMap.get("email"));
               if (Objects.isNull(user)) {
                   userRepository.save(getUserFromMap(requestMap));
                   return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
               } else {
                   return CafeUtils.getResponseEntity("Email is already exists in the database", HttpStatus.BAD_REQUEST);
               }
           } else {
               return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
           }
       }catch (Exception exception){
           exception.printStackTrace();
       }
       return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Validation des données
    private boolean validateSignUpMap(Map<String, String> requestMap){
     if (  requestMap.containsKey("name") && requestMap.containsKey("phone") && requestMap.containsKey("email") && requestMap.containsKey("password")){
         return true;
     }
     return false;
    }


    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setPhone(requestMap.get("phone"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");

        return user;
    }
}

package com.enosnery.desafiojava.controller;

import com.enosnery.desafiojava.model.User;
import com.enosnery.desafiojava.request.AddUserRequest;
import com.enosnery.desafiojava.request.LoginRequest;
import com.enosnery.desafiojava.response.UserResponse;
import com.enosnery.desafiojava.service.UserService;
import com.enosnery.desafiojava.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping("user")
    public ResponseEntity<?> addUsers(@RequestBody AddUserRequest userForm){
        Object addUserObject = userService.addUser(userForm);
        if(addUserObject instanceof User){
            return new ResponseEntity<>(new UserResponse((User) addUserObject), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Constants.EMAIL_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Object loginObject = userService.login(request);
        if(loginObject instanceof User){
            return new ResponseEntity<>(new UserResponse((User) loginObject), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Constants.NOT_AUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("user/{guid}")
    public ResponseEntity<?> getUserProfile(@RequestHeader String Authorization, @PathVariable String guid){
        Object validationObject = userService.validateUser(guid, Authorization);
        if(validationObject instanceof User){
            return new ResponseEntity<>(new UserResponse((User) validationObject), HttpStatus.OK);
        }else {
            return new ResponseEntity<>((String) validationObject, HttpStatus.OK);
        }
    }

}

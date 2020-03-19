package com.enosnery.desafiojava.controller;

import com.enosnery.desafiojava.model.User;
import com.enosnery.desafiojava.request.AddUserRequest;
import com.enosnery.desafiojava.response.NewUserResponse;
import com.enosnery.desafiojava.service.UserService;
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

        User newUser = userService.addUser(userForm);

        return new ResponseEntity<>(new NewUserResponse(newUser), HttpStatus.OK);
    }

}

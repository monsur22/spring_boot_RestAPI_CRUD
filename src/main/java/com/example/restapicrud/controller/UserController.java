package com.example.restapicrud.controller;

import com.example.restapicrud.model.User;
import com.example.restapicrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/registration")
    public User registrationUser(@RequestBody User user){
         return userService.registrationUser(user);
    }

    @GetMapping("/encode")
    public void encoder(){
        String rawpass = "pass";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePass = passwordEncoder.encode(rawpass);
        System.out.println(encodePass);
    }

}

package com.example.restapicrud.controller;

import com.example.restapicrud.model.ConfirmationToken;
import com.example.restapicrud.model.User;
import com.example.restapicrud.repository.ConfirmationTokenRepository;
import com.example.restapicrud.repository.UserRepository;
import com.example.restapicrud.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @PostMapping("/user/registration")
    public String registrationUser(@RequestBody User user) {
        return userService.registrationUser(user);
    }

    @GetMapping("/verify/{code}")
    public String verifyUser(@PathVariable String code) {
        if (userService.emailVerify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }


    @PostMapping("/confirm-code/{token}")
    public ResponseEntity<String> confirmUserAccount(@PathVariable String token) {

        Optional<ConfirmationToken> confirmToken = userService.getConfirmationToken(token);

        if (confirmToken.isPresent()) {
            User user = confirmToken.get().getUser();
            user.setActive(true);
            userRepository.save(user);
            return new ResponseEntity<>("Verified done", HttpStatus.OK);

        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody User user){
        return userService.userLogin(user);
    }

    @GetMapping("/encode")
    public void encoder() {
        String rawpass = "pass";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePass = passwordEncoder.encode(rawpass);

        System.out.println(encodePass);

        String randomCode = RandomString.make(60);

        System.out.println(randomCode);

    }
}

package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.User;
import com.example.restapicrud.repository.UserRepository;
import com.example.restapicrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registrationUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        user.setName(user.getName());
//        user.setEmail(user.getEmail());
//        user.setVerificationcode(user.getVerificationcode());
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        return userRepository.save(user);
//        return user;
    }
}

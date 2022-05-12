package com.example.restapicrud.service;

import com.example.restapicrud.model.EmailDetails;
import com.example.restapicrud.model.User;

public interface UserService {
    User registrationUser(User user);
    String sendSimpleMail(EmailDetails details,User user);
}

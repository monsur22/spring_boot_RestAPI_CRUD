package com.example.restapicrud.service;

import com.example.restapicrud.model.ConfirmationToken;
import com.example.restapicrud.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    String registrationUser(User user);

    String sendSimpleMail(User user);

    boolean emailVerify(String code);

    Optional<ConfirmationToken> getConfirmationToken(String token);

    String userLogin(User user);

}

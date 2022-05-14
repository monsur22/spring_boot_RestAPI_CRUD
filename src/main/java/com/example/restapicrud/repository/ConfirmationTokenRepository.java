package com.example.restapicrud.repository;

import com.example.restapicrud.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    List<ConfirmationToken> findByUserEmail(String email);

    List<ConfirmationToken> findByToken(String token);

    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);
}

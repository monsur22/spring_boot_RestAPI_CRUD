package com.example.restapicrud.repository;

import com.example.restapicrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    List<User> findByEmail(String email);
//    @Query("SELECT u  FROM User u WHERE u.verificationcode = ?1")
//    User findByVerificationCode( String verificationcode);
    Optional<User> findUserByVerificationcode(String code);
}

package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.ConfirmationToken;
import com.example.restapicrud.model.User;
import com.example.restapicrud.repository.ConfirmationTokenRepository;
import com.example.restapicrud.repository.UserRepository;
import com.example.restapicrud.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired  private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    @Override
    public String registrationUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String randomCode = RandomString.make(60);
        user.setVerificationcode(randomCode);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        userRepository.save(user);
//        insert into token table
        List<ConfirmationToken> verificationTokens = confirmationTokenRepository.findByUserEmail(user.getEmail());
        ConfirmationToken verificationToken;
        if (verificationTokens.isEmpty()) {
            verificationToken = new ConfirmationToken();
            verificationToken.setUser(user);
            verificationToken.setToken(randomCode);
            confirmationTokenRepository.save(verificationToken);
        } else {
            verificationToken = verificationTokens.get(0);
        }
        //Email Send
        sendSimpleMail(user);
//        return userRepository.save(user);
        return "save data";

    }


    @Override
    public String sendSimpleMail(User user){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
//            mailMessage.setText("\r\n" + "http://localhost:8080/email-verify?token=" + user.getVerificationcode());
            mailMessage.setText("\r\n" + "http://localhost:8080/verify/email?token=" + user.getVerificationcode());

            mailMessage.setSubject("User Registration");
            javaMailSender.send(mailMessage);
            return "Mail sent SuccessFully...";

        } catch (Exception e){
            return "Error while sending Mail";
        }
    }

//    @Override
//    public boolean emailVerify(String code){
//        User user = userRepository.findByVerificationCode(code);
//
//        if(user != null)
//        {
//            user.setVerificationcode(null);
//            user.setActive(true);
//            userRepository.save(user);
////            modelAndView.setViewName("accountVerified");
//            System.out.println("token valid");
//            return true;
//        }
//        else
//        {
////            modelAndView.addObject("message","The link is invalid or broken!");
////            modelAndView.setViewName("error");
//            System.out.println("Token Not valid");
//            return  false;
//        }
////        System.out.println(verificationTokens);
////            return "HEllo";
//    }

    @Override
    public boolean emailVerify(String code) {
        Optional<User> user = userRepository.findUserByVerificationcode(code);

        System.out.println(user);

        if (user.isEmpty() ) {
            return false;
        } else {
            user.get().setActive(true);
            userRepository.save(user.get());

            return true;
        }

    }
    @Override
    public ResponseEntity<String> verifyEmail(String token){
        List<ConfirmationToken> verificationTokens = confirmationTokenRepository.findByToken(token);
        System.out.println(verificationTokens);
        if (verificationTokens.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }
        ConfirmationToken verificationToken = verificationTokens.get(0);
        verificationToken.getUser().setActive(true);
        confirmationTokenRepository.save(verificationToken);
//        if (verificationTokens == null ) {
//            return false;
//        } else {
//            verificationTokens.setVerificationcode(null);
//            verificationTokens.setActive(true);
//            userRepository.save(verificationTokens);
//
//            return true;
//        }
//        if (verificationTokens.isEmpty()) {
//            return ResponseEntity.badRequest().body("Invalid token.");
//        }
//
//        ConfirmationToken verificationToken = verificationTokens.get(0);
//        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
//            return ResponseEntity.unprocessableEntity().body("Expired token.");
//        }
//
//        verificationToken.setConfirmedDateTime(LocalDateTime.now());
//        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
//        verificationToken.getUser().setIsActive(true);
//        confirmationTokenRepository.save(verificationToken);

        return ResponseEntity.ok("You have successfully verified your email address.");
    }

    @Override
    public Optional<ConfirmationToken> getConfirmationToken(String token) {
        return confirmationTokenRepository.findConfirmationTokenByToken(token);
    }


}

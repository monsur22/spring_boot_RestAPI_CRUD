package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.ConfirmationToken;
import com.example.restapicrud.model.User;
import com.example.restapicrud.repository.ConfirmationTokenRepository;
import com.example.restapicrud.repository.UserRepository;
import com.example.restapicrud.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

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
    public String sendSimpleMail(User user) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
//            mailMessage.setText("\r\n" + "http://localhost:8080/email-verify/=" + user.getVerificationcode());
            mailMessage.setText("\r\n" + "http://localhost:8080/verify/" + user.getVerificationcode());

            mailMessage.setSubject("User Registration");
            javaMailSender.send(mailMessage);
            return "Mail sent SuccessFully...";

        } catch (Exception e) {
            return "Error while sending Mail";
        }
    }


    @Override
    public boolean emailVerify(String code) {
        Optional<User> user = userRepository.findUserByVerificationcode(code);

        System.out.println(user);

        if (user.isEmpty()) {
            return false;
        } else {
            user.get().setActive(true);
            userRepository.save(user.get());

            return true;
        }

    }

    @Override
    public Optional<ConfirmationToken> getConfirmationToken(String token) {
        return confirmationTokenRepository.findConfirmationTokenByToken(token);
    }

    @Override
    public String userLogin(User user){
        Optional<User> getUser = userRepository.findUserByEmail(user.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String putPassword = passwordEncoder.encode(user.getPassword());
        System.out.println(getUser.get().getPassword());
        System.out.println(user.getPassword());
        System.out.println(putPassword);

    if(getUser.isPresent()){
//        if (putPassword.equals(getUser.get().getPassword())){
            if (passwordEncoder.matches(user.getPassword(),getUser.get().getPassword())){
            return "true";
        }
        else{
            return "password invalid";
        }
    }
        return "email not valid";


    }

}

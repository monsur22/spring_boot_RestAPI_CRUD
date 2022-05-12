package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.EmailDetails;
import com.example.restapicrud.model.User;
import com.example.restapicrud.repository.UserRepository;
import com.example.restapicrud.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired  private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    @Override
    public User registrationUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String randomCode = RandomString.make(60);
        user.setVerificationcode(randomCode);
//        user.setActive(true);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
//        sendSimpleMail(details,user);
//        user.setEmail(user.getEmail());
//        System.out.println( user);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
//            mailMessage.setText(details.getMsgBody());
            mailMessage.setText(user.getVerificationcode());
            mailMessage.setSubject("User Registration");

            javaMailSender.send(mailMessage);

            return userRepository.save(user);


//        return userRepository.save(user);
    }


    @Override
    public String sendSimpleMail(EmailDetails details,User user){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
//            mailMessage.setTo(details.getRecipient());
            mailMessage.setTo(user.getEmail());
//            mailMessage.setText(details.getMsgBody());
            mailMessage.setText(user.getVerificationcode());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail sent SuccessFully...";

        } catch (Exception e){
            return "Error while sending Mail";
        }
    }
}

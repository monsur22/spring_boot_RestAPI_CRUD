package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.EmailDetails;
import com.example.restapicrud.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired  private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    @Override
    public String sendSimpleMail(EmailDetails details){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail sent SuccessFully...";

        } catch (Exception e){
            return "Error while sending Mail";
        }
    }
}

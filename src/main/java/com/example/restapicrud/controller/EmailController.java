package com.example.restapicrud.controller;

import com.example.restapicrud.model.EmailDetails;
import com.example.restapicrud.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @PostMapping("/sendEmail")
    public String sendMail(@RequestParam EmailDetails details){
//        String status = emailService.sendSimpleMail(details);
//        return status;
        return "Email sent successfully";
    }
    @PostMapping("/sendEmails")
    public String sendMails(@RequestBody EmailDetails details){
    return emailService.sendSimpleMail(details);
//        return "Email sent successfully";
    }

    @GetMapping("/hello")
    public String test(){
        return "get";
    }

}

package com.example.restapicrud.service;

import com.example.restapicrud.model.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
//    String sendMailWithAttachment(EmailDetails details);
}

package com.grinder.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

    void sendEmail(String toEmail, String title, String text);
}

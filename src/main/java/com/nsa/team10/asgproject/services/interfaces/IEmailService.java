package com.nsa.team10.asgproject.services.interfaces;

import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface IEmailService
{
    void sendSimpleMessage(String to, String subject, String text);
    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, List<String> templateArgs);
    void sendMessageWithAttachment(String to, String subject, String text, String fileName, String pathToAttachment);
}

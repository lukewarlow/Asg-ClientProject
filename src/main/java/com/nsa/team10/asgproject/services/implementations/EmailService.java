package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.services.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

@Component
public class EmailService implements IEmailService
{
    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender)
    {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text)
    {
        try
        {
            var message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom("asgsystem@example.com");
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        }
        catch (MailException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, List<String> templateArgs)
    {
        var text = String.format(template.getText(), templateArgs);
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String fileName, String pathToAttachment)
    {
        try
        {
            var message = emailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setFrom("asgsystem@example.com");
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(fileName, file);

            emailSender.send(message);
        }
        catch (MessagingException ex)
        {
            ex.printStackTrace();
        }
    }
}

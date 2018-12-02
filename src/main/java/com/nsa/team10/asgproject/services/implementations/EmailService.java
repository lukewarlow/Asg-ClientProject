package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.services.dtos.Mail;
import com.nsa.team10.asgproject.services.interfaces.IEmailService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class EmailService implements IEmailService
{
    private final JavaMailSender emailSender;

    private final Configuration freemarkerConfig;

    @Autowired
    public EmailService(JavaMailSender emailSender, Configuration freemarkerConfig)
    {
        this.emailSender = emailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Override
    public void sendSimpleEmail(Mail mail)
    {
        try
        {
            var message = new SimpleMailMessage();
            message.setTo(mail.getTo());
            message.setFrom(mail.getFrom());
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());

            emailSender.send(message);
        }
        catch (MailException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendEmail(Mail mail, String pathToEmailTemplate)
    {
        try
        {
            var message = emailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message);

            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            var template = freemarkerConfig.getTemplate("/emails/" + pathToEmailTemplate);
            var text = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());

            helper.setTo(mail.getTo());
            helper.setFrom(mail.getFrom());
            helper.setText(text, true);
            helper.setSubject(mail.getSubject());

            emailSender.send(message);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

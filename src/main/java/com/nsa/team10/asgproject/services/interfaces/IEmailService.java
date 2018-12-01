package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.services.dtos.Mail;

public interface IEmailService
{
    void sendSimpleEmail(Mail mail);
    void sendEmail(Mail mail, String pathToEmailTemplate);
}

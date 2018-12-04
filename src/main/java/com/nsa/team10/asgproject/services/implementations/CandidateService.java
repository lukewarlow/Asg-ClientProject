package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.services.dtos.Mail;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
import com.nsa.team10.asgproject.services.interfaces.ICandidateService;
import com.nsa.team10.asgproject.services.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService
{
    private final ICandidateRepository candidateRepository;
    private final IEmailService emailService;

    @Autowired
    public CandidateService(ICandidateRepository candidateRepository, IEmailService emailService)
    {
        this.candidateRepository = candidateRepository;
        this.emailService = emailService;
    }

    public void create(NewCandidateDto newCandidate)
    {
        if (!getCurrentUserDetails().isPresent())
            throw new AccessDeniedException("Need to be logged in");

        var userId = getCurrentUserDetails().get().getUser().getId();
        candidateRepository.create(userId, newCandidate);
    }

    @Override
    public void sendReceipt(boolean hasPayed)
    {
        var userDetails = getCurrentUserDetails();
        if (userDetails.isPresent())
        {
            var mail = new Mail();
            mail.setTo(userDetails.get().getUsername());
            mail.setSubject("Payment Received");
            var model = new HashMap<String, Object>();
            model.put("link", "localhost:8080");
            mail.setModel(model);
            emailService.sendEmail(mail, "receipt.ftl");

            candidateRepository.setHasPayed(true);
        }
    }

    private Optional<DefaultUserDetails> getCurrentUserDetails()
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return Optional.of((DefaultUserDetails) authentication.getPrincipal());
        else return Optional.empty();
    }
}

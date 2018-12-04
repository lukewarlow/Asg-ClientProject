package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.services.dtos.Mail;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.interfaces.IReceiptService;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class ReceiptService implements IReceiptService, UserDetailsService
{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @Value("${server.basedomain}")
    private String userBucketPath;

    @Autowired
    public ReceiptService(IUserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public void receipt(String email)
    {
        var mail = new Mail();
        mail.setTo(email);
        mail.setSubject("Payment Received");
        var model = new HashMap<String, Object>();
        model.put("link", "localhost:8080");
        mail.setModel(model);
        emailService.sendEmail(mail, "receipt.ftl");
    }

    @Override
    public boolean verifyActivationToken(String email, String token)
    {
        return userRepository.verifyActivationToken(email, token);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        var user = userRepository.findWithPasswordByEmail(email);
        if (!user.isPresent()) throw new UsernameNotFoundException(email);
        else return new DefaultUserDetails(user.get());
    }
}

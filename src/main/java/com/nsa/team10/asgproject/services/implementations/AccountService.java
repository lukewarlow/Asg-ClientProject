package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IAccountRepository;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService
{
    private final IAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(IAccountRepository accountRepository, PasswordEncoder passwordEncoder)
    {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(NewUserDto newUser)
    {
        var hashedPassword = passwordEncoder.encode(newUser.getPassword());
        var user = new UserDao(newUser.getForename(), newUser.getSurname(), newUser.getEmail(), newUser.getPhoneNumber(), UserDao.Role.Candidate);
        accountRepository.register(user, hashedPassword);
    }
}

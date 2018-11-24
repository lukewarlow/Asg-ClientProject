package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService, UserDetailsService
{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(IUserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(NewUserDto newUser) throws UserConflictException
    {
        var hashedPassword = passwordEncoder.encode(newUser.getPassword());
        var user = new UserDao(newUser.getForename(), newUser.getSurname(), newUser.getEmail().toLowerCase(), newUser.getPhoneNumber(), UserDao.Role.Candidate);
        userRepository.register(user, hashedPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        var user = userRepository.getUserWithPasswordByEmail(email);
        if (!user.isPresent()) throw new UsernameNotFoundException(email);
        else return new DefaultUserDetails(user.get());
    }
}

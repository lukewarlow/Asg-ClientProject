package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IReceiptService
{
    void receipt(String email);
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    boolean verifyActivationToken(String email, String token);
}

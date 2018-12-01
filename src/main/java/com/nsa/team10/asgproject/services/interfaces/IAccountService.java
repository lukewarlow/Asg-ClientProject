package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.validation.ConflictException;

public interface IAccountService
{
    void register(NewUserDto newUser) throws ConflictException;
    boolean verifyActivationToken(String email, String token);
}

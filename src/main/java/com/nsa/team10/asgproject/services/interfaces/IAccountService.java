package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewUserDto;

public interface IAccountService
{
    void register(NewUserDto newUser);
}

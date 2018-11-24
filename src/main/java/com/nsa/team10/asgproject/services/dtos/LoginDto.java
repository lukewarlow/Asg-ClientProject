package com.nsa.team10.asgproject.services.dtos;

import javax.validation.constraints.NotNull;

public class LoginDto
{
    @NotNull
    private String email;
    @NotNull
    private String password;

    public LoginDto(@NotNull String email, @NotNull String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}

package com.nsa.team10.asgproject.services.dtos;

import com.nsa.team10.asgproject.validation.PasswordMatches;

import javax.validation.constraints.NotNull;

@PasswordMatches
public class NewUserDto
{
    @NotNull
    private String forename;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    private String phoneNumber;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;

    public NewUserDto(@NotNull String forename, @NotNull String surname, @NotNull String email, String phoneNumber, @NotNull String password, @NotNull String confirmPassword)
    {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getForename()
    {
        return forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }
}

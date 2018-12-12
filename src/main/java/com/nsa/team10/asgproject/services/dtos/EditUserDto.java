package com.nsa.team10.asgproject.services.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditUserDto
{
    @NotNull
    @NotEmpty
    private String forename;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String phoneNumber;
    private int role;

    public EditUserDto(@NotNull @NotEmpty String forename, @NotNull @NotEmpty String surname, @NotNull @NotEmpty String email, @NotNull @NotEmpty String phoneNumber, int role)
    {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public int getRole()
    {
        return role;
    }
}

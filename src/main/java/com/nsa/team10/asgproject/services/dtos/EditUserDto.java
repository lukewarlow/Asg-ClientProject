package com.nsa.team10.asgproject.services.dtos;

import com.nsa.team10.asgproject.repositories.daos.UserDao;

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
    private UserDao.Role role;

    public EditUserDto(@NotNull @NotEmpty String forename, @NotNull @NotEmpty String surname, @NotNull @NotEmpty String email, @NotNull @NotEmpty String phoneNumber, @NotNull @NotEmpty String role)
    {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        switch(role)
        {
            case "Guest":
                this.role = UserDao.Role.Guest;
                break;
            case "Candidate":
                this.role = UserDao.Role.Candidate;
                break;
            case "Instructor":
                this.role = UserDao.Role.Instructor;
                break;
            case "Admin":
                this.role = UserDao.Role.Admin;
                break;
            default:
                throw new IllegalArgumentException(role + " is not a valid user role.");
        }
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

    public UserDao.Role getRole()
    {
        return role;
    }
}

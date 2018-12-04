package com.nsa.team10.asgproject.repositories.daos;

public class CompanyDao
{
    private long id;
    private String name;
    private String email;
    private String phoneNumber;

    public CompanyDao(long id, String name, String email, String phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public CompanyDao(String name, String email, String phoneNumber)
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}

package com.nsa.team10.asgproject.dal.daos;

public class UserWithPasswordDao extends UserDao
{
    private String password;

    public UserWithPasswordDao(long id, String forename, String surname, String email, String phoneNumber, Role role, String password)
    {
        super(id, forename, surname, email, phoneNumber, role);
        this.password = password;
    }

    public UserWithPasswordDao(String forename, String surname, String email, String phoneNumber, Role role, String password)
    {
        super(forename, surname, email, phoneNumber, role);
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}

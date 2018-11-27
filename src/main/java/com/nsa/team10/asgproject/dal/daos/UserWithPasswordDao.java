package com.nsa.team10.asgproject.dal.daos;

public class UserWithPasswordDao extends UserDao
{
    private String password;

    public UserWithPasswordDao(long id, String forename, String surname, String email, String phoneNumber, Role role, boolean disabled, String password)
    {
        super(id, forename, surname, email, phoneNumber, role, disabled);
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}

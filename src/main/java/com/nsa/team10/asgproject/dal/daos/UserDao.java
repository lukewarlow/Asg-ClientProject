package com.nsa.team10.asgproject.dal.daos;

public class UserDao
{
    private long id;
    private String forename;
    private String surname;
    private String email;
    private String phoneNumber;
    private Role role;
    private boolean disabled;

    public UserDao(long id, String forename, String surname, String email, String phoneNumber, Role role, boolean disabled)
    {
        this(forename, surname, email, phoneNumber, role);
        this.id = id;
        this.disabled = disabled;
    }

    public UserDao(String forename, String surname, String email, String phoneNumber, Role role)
    {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public long getId()
    {
        return id;
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

    public Role getRole()
    {
        return role;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public enum Role
    {
        Candidate,
        Instructor,
        Admin
    }
}

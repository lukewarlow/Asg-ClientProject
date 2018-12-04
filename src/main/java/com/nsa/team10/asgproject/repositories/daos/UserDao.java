package com.nsa.team10.asgproject.repositories.daos;

public class UserDao
{
    private long id;
    private String forename;
    private String surname;
    private String email;
    private String phoneNumber;
    private Role role;
    private boolean activated;
    private boolean disabled;
    private String createdAt;
    private String updatedAt;

    public UserDao(long id, String forename, String surname, String email, String phoneNumber, Role role, boolean activated, boolean disabled, String createdAt, String updatedAt)
    {
        this(forename, surname, email, phoneNumber, role);
        this.id = id;
        this.activated = activated;
        this.disabled = disabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public boolean isActivated()
    {
        return activated;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public enum Role
    {
        Guest,
        Candidate,
        Instructor,
        Admin
    }
}

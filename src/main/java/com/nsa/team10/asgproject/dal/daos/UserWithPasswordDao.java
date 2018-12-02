package com.nsa.team10.asgproject.dal.daos;

public class UserWithPasswordDao extends UserDao
{
    private String password;

    public UserWithPasswordDao(long id, String forename, String surname, String email, String phoneNumber, Role role, boolean activated, boolean disabled, String createdAt, String updatedAt, String password)
    {
        super(id, forename, surname, email, phoneNumber, role, activated, disabled, createdAt, updatedAt);
        this.password = password;
    }

    public UserDao getWithoutPassword()
    {
        return new UserDao(super.getId(), super.getForename(), super.getSurname(), super.getEmail(), super.getPhoneNumber(), super.getRole(), super.isActivated(), super.isDisabled(), super.getCreatedAt(), super.getUpdatedAt());
    }

    public String getPassword()
    {
        return password;
    }
}

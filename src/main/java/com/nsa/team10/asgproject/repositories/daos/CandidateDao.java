package com.nsa.team10.asgproject.repositories.daos;

public class CandidateDao
{
    private long id;
    private String candidateNumber;
    private UserDao userDao;
    private GSCourseLocationDao preferedLocation;
    private String dob;
    private AddressDao address;
    private CompanyDao company;
    private String flyingExperience;
    private DroneDao drone;
    private Boolean hasPayed;

    public CandidateDao(long id, String candidateNumber, UserDao userDao, GSCourseLocationDao preferedLocation, String dob, AddressDao address, CompanyDao company, String flyingExperience, DroneDao drone, boolean hasPayed)
    {
        this.id = id;
        this.candidateNumber = candidateNumber;
        this.userDao = userDao;
        this.preferedLocation = preferedLocation;
        this.dob = dob;
        this.address = address;
        this.company = company;
        this.flyingExperience = flyingExperience;
        this.drone = drone;
        this.hasPayed = hasPayed;
    }

    public CandidateDao(String candidateNumber, UserDao userDao, GSCourseLocationDao preferedLocation, String dob, AddressDao address, CompanyDao company, String flyingExperience, DroneDao drone)
    {
        this.candidateNumber = candidateNumber;
        this.userDao = userDao;
        this.preferedLocation = preferedLocation;
        this.dob = dob;
        this.address = address;
        this.company = company;
        this.flyingExperience = flyingExperience;
        this.drone = drone;
    }

    public long getId()
    {
        return id;
    }

    public String getCandidateNumber()
    {
        return candidateNumber;
    }

    public UserDao getUser()
    {
        return userDao;
    }

    public GSCourseLocationDao getPreferedLocation()
    {
        return preferedLocation;
    }

    public String getDateOfBirth()
    {
        return dob;
    }

    public AddressDao getAddress()
    {
        return address;
    }

    public CompanyDao getCompany()
    {
        return company;
    }

    public String getFlyingExperience()
    {
        return flyingExperience;
    }

    public DroneDao getDrone()
    {
        return drone;
    }

    public boolean isPayed()
    {
        return hasPayed;
    }
}

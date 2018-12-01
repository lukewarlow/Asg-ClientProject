package com.nsa.team10.asgproject.services.dtos;

import com.nsa.team10.asgproject.dal.daos.DroneDao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Random;

public class NewCandidateDto
{
    private String preferedLocation;
    private String dob;
    private String placeBirth;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String postcode;
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String flyingExperience;
    private long droneId;

    public NewCandidateDto(@NotNull @NotEmpty String preferedLocation,
                           @NotNull @NotEmpty String dob,
                           @NotNull @NotEmpty String placeBirth,
                           @NotNull @NotEmpty String address1,
                           String address2,
                           @NotNull @NotEmpty String city,
                           @NotNull @NotEmpty String postcode,
                           @NotNull @NotEmpty String county,
                           String companyName,
                           String companyEmail,
                           String companyPhone,
                           @NotNull @NotEmpty String flyingExperience,
                           @NotNull long droneId)
    {
        this.preferedLocation = preferedLocation;
        this.dob = dob;
        this.placeBirth = placeBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postcode = postcode.replaceAll("\\s+","");
        this.county = county;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.flyingExperience = flyingExperience;
        this.droneId = droneId;
    }

    public String getPreferedLocation()
    {
        return preferedLocation;
    }

    public String getDateOfBirth()
    {
        return dob;
    }

    public String getPlaceBirth()
    {
        return placeBirth;
    }

    public String getAddress1()
    {
        return address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public String getCity()
    {
        return city;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public String getCounty()
    {
        return county;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public String getCompanyEmail()
    {
        return companyEmail;
    }

    public String getCompanyPhone()
    {
        return companyPhone;
    }

    public String getFlyingExperience()
    {
        return flyingExperience;
    }

    public long getDroneId()
    {
        return droneId;
    }
}

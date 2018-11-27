package com.nsa.team10.asgproject.services.dtos;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

public class NewBookingDto
{
    private String startDate;
    private String endDate;
    private String location;
    private String courseType;
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
    private String droneManufacturer;
    private String droneModel;

    public NewBookingDto(@NotNull String startDate,
                         @NotNull String endDate,
                         @NotNull String location,
                         @NotNull String courseType,
                         @NotNull String dob,
                         @NotNull String placeBirth,
                         @NotNull String address1,
                         String address2,
                         @NotNull String city,
                         @NotNull String postcode,
                         @NotNull String county,
                         String companyName,
                         String companyEmail,
                         String companyPhone,
                         @NotNull String flyingExperience,
                         @NotNull String droneManufacturer,
                         @NotNull String droneModel)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.courseType = courseType;
        this.dob = dob;
        this.placeBirth = placeBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postcode = postcode;
        this.county = county;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.flyingExperience = flyingExperience;
        this.droneManufacturer = droneManufacturer;
        this.droneModel = droneModel;
    }

    public String getCandidateReferenceNumber()
    {
        //TODO implement this jobie
//        return "ASG-" + someshit;
        return Long.toString(new Random().nextLong());
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public String getLocation()
    {
        return location;
    }

    public String getCourseType()
    {
        return courseType;
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

    public String getDroneManufacturer()
    {
        return droneManufacturer;
    }

    public String getDroneModel()
    {
        return droneModel;
    }

    //testing purposes
    @Override
    public String toString()
    {
        return this.startDate + this.endDate + this.location + this.courseType + this.birthDate + this.placeBirth + this.address1 + this.address2 + this.city + this.postcode + this.county + this.companyName + this.companyEmail + this.companyPhone + this.flyingExperience + this.droneManufacturer + this.droneModel;
    }
}

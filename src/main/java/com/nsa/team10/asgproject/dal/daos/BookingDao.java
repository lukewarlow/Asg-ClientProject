package com.nsa.team10.asgproject.dal.daos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookingDao
{
    private String startDate;
    private String endDate;
    private String location;
    private String courseType;
    private String dateBirth;
    private String placeBirth;
    private String address1;
    private String address2;
    private String address3;
    private String postCode;
    private String county;
    private String country;
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String flyExperiance;
    private String droneManufacturer;
    private String droneModel;

    public BookingDao( String startDate,
                       String endDate,
                       String location,
                       String courseType,
                       String dateBirth,
                       String placeBirth,
                       String address1,
                       String address2,
                       String address3,
                       String postCode,
                       String county,
                       String country,
                       String companyName,
                       String companyEmail,
                       String companyPhone,
                       String flyExperiance,
                       String droneManufacturer,
                       String droneModel)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.courseType = courseType;
        this.dateBirth = dateBirth;
        this.placeBirth = placeBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postCode = postCode;
        this.county = county;
        this.country = country;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.flyExperiance = flyExperiance;
        this.droneManufacturer = droneManufacturer;
        this.droneModel = droneModel;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public String getCourseType() {
        return courseType;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getFlyExperiance() {
        return flyExperiance;
    }

    public String getDroneManufacturer() {
        return droneManufacturer;
    }

    public String getDroneModel() {
        return droneModel;
    }

}

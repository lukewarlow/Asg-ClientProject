package com.nsa.team10.asgproject.repositories.daos;

public class AddressDao
{
    private long id;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String postcode;

    public AddressDao(long id, String address1, String address2, String city, String county, String postcode)
    {
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }

    public AddressDao(String address1, String address2, String city, String county, String postcode)
    {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }

    public long getId()
    {
        return id;
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

    public String getCounty()
    {
        return county;
    }

    public String getPostcode()
    {
        return postcode;
    }
}

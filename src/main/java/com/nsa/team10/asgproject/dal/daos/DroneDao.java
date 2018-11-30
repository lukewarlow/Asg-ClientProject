package com.nsa.team10.asgproject.dal.daos;

public class DroneDao
{
    private long id;
    private String manufacturer;
    private String model;

    public DroneDao(long id, String manufacturer, String model)
    {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public DroneDao(String manufacturer, String model)
    {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public long getId()
    {
        return id;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getModel()
    {
        return model;
    }
}

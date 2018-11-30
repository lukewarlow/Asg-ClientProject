package com.nsa.team10.asgproject.services.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewDroneDto
{
    private String manufacturer;
    private String model;

    public NewDroneDto(@NotNull @NotEmpty String manufacturer, @NotNull @NotEmpty String model)
    {
        this.manufacturer = manufacturer;
        this.model = model;
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

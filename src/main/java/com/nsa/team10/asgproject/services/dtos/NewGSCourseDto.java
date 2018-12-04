package com.nsa.team10.asgproject.services.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewGSCourseDto
{
    private String startDate;
    private String endDate;
    private long typeId;
    private long locationId;

    public NewGSCourseDto(@NotNull @NotEmpty String startDate, @NotNull @NotEmpty String endDate, @NotNull long typeId, @NotNull long locationId)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeId = typeId;
        this.locationId = locationId;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public long getTypeId()
    {
        return typeId;
    }

    public long getLocationId()
    {
        return locationId;
    }
}

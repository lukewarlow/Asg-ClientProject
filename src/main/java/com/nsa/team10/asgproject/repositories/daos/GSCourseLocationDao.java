package com.nsa.team10.asgproject.repositories.daos;

public class GSCourseLocationDao
{
    private long id;
    private String location;

    public GSCourseLocationDao(long id, String location)
    {
        this.id = id;
        this.location = location;
    }

    public GSCourseLocationDao(long id)
    {
        this.id = id;
    }

    public GSCourseLocationDao(String location)
    {
        this.location = location;
    }

    public long getId()
    {
        return id;
    }

    public String getLocation()
    {
        return location;
    }
}

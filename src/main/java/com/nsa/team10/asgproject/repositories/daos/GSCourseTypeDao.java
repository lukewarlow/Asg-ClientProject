package com.nsa.team10.asgproject.repositories.daos;

public class GSCourseTypeDao
{
    private long id;
    private String type;

    public GSCourseTypeDao(long id, String type)
    {
        this.id = id;
        this.type = type;
    }

    public GSCourseTypeDao(long id)
    {
        this.id = id;
    }

    public GSCourseTypeDao(String type)
    {
        this.type = type;
    }

    public long getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }
}

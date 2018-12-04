package com.nsa.team10.asgproject.repositories.daos;

public class GSCourseDao
{
    private long id;
    private String courseNumber;
    private String startDate;
    private String endDate;
    private GSCourseTypeDao type;
    private GSCourseLocationDao location;

    public GSCourseDao(long id, String courseNumber, String startDate, String endDate, GSCourseTypeDao type, GSCourseLocationDao location)
    {
        this.id = id;
        this.courseNumber = courseNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.location = location;
    }

    public GSCourseDao(String startDate, String endDate, GSCourseTypeDao type, GSCourseLocationDao location)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.location = location;
    }

    public long getId()
    {
        return id;
    }

    public String getCourseNumber()
    {
        return courseNumber;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public GSCourseTypeDao getType()
    {
        return type;
    }

    public GSCourseLocationDao getLocation()
    {
        return location;
    }
}

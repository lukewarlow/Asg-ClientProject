package com.nsa.team10.asgproject.repositories.daos;

public class CountDao
{
    private long id;
    private long count;

    public CountDao(long id, long count)
    {
        this.id = id;
        this.count = count;
    }

    public long getId()
    {
        return id;
    }
    public long getCount()
    {
        return count;
    }
}

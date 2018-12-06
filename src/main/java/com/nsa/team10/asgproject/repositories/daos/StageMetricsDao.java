package com.nsa.team10.asgproject.repositories.daos;

public class StageMetricsDao
{
    private long id;
    private String stage;
    private long total;

    public StageMetricsDao(long id, String stage, long total)
    {
        this.id = id;
        this.stage = stage;
        this.total = total;
    }

    public long getStageId()
    {
        return id;
    }

    public String getStage()
    {
        return stage;
    }

    public long getTotal()
    {
        return total;
    }
}

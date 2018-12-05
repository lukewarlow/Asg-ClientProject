package com.nsa.team10.asgproject.repositories.daos;

public class CandidateProcessStageDao
{
    private long id;
    private String stage;

    public CandidateProcessStageDao(long id, String stage)
    {
        this.id = id;
        this.stage = stage;
    }

    public long getId()
    {
        return id;
    }

    public String getStage()
    {
        return stage;
    }
}

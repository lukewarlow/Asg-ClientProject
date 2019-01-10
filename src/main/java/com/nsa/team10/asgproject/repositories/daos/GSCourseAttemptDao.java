package com.nsa.team10.asgproject.repositories.daos;

public class GSCourseAttemptDao
{
    private long id;
    private GSCourseDao gsCourse;
    private CandidateDao candidate;
    private byte questionBank;
    private String markedDate;
    private int result;

    public GSCourseAttemptDao(long id, GSCourseDao gsCourse, CandidateDao candidate, byte questionBank, String markedDate, int result)
    {
        this.id = id;
        this.gsCourse = gsCourse;
        this.candidate = candidate;
        this.questionBank = questionBank;
        this.markedDate = markedDate;
        this.result = result;
    }

    public long getId()
    {
        return id;
    }

    public GSCourseDao getCourse()
    {
        return gsCourse;
    }

    public CandidateDao getCandidate()
    {
        return candidate;
    }

    public byte getQuestionBank()
    {
        return questionBank;
    }

    public String getMarkedDate()
    {
        return markedDate;
    }

    public int getResult()
    {
        return result;
    }
}

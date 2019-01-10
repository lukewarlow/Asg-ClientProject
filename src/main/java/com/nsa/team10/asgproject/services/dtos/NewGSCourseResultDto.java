package com.nsa.team10.asgproject.services.dtos;

import javax.validation.constraints.NotNull;

public class NewGSCourseResultDto
{
    private long gsCourseId;
    private long candidateId;
    private byte questionBank;
    private int result;

    public NewGSCourseResultDto(@NotNull long gsCourseId, @NotNull long candidateId, @NotNull byte questionBank, @NotNull int result)
    {
        this.gsCourseId = gsCourseId;
        this.candidateId = candidateId;
        this.questionBank = questionBank;
        this.result = result;
    }

    public long getGSCourseId()
    {
        return gsCourseId;
    }

    public long getCandidateId()
    {
        return candidateId;
    }

    public byte getQuestionBank()
    {
        return questionBank;
    }

    public int getResult()
    {
        return result;
    }
}

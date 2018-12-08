package com.nsa.team10.asgproject.repositories.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewGSCourseResultDto;

public interface IResultsRepository
{
    void submitGSCourseResults(NewGSCourseResultDto result);
}

package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.repositories.interfaces.IResultsRepository;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseResultDto;
import com.nsa.team10.asgproject.services.interfaces.IResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResultsService implements IResultsService
{
    private final IResultsRepository resultsRepository;

    @Autowired
    public ResultsService(IResultsRepository resultsRepository)
    {
        this.resultsRepository = resultsRepository;
    }

    @Override
    public void submitGSCourseResults(NewGSCourseResultDto result)
    {
        resultsRepository.submitGSCourseResults(result);
    }
}

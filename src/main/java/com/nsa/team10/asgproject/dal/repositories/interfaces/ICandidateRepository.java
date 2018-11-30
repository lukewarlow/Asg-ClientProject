package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;

public interface ICandidateRepository
{
    void create(long userId, NewCandidateDto newCandidate);
}

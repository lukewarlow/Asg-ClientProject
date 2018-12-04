package com.nsa.team10.asgproject.repositories.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;

public interface ICandidateRepository
{
    void create(long userId, NewCandidateDto newCandidate);
    PaginatedList<CandidateDao> findAll(FilteredPageRequest pageRequest);
    boolean setHasPayed(long userId, boolean hasPayed);
}

package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;

import java.util.Optional;

public interface ICandidateService
{
    void create(NewCandidateDto newCandidate);

    PaginatedList<CandidateDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<CandidateDao> findAllNeedAssigning(FilteredPageRequest pageRequest);

    Optional<CandidateDao> findById(long candidateId);
}

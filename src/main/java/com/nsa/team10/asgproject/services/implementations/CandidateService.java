package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
import com.nsa.team10.asgproject.services.interfaces.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService implements ICandidateService
{
    private final ICandidateRepository candidateRepository;

    @Autowired
    public CandidateService(ICandidateRepository candidateRepository)
    {
        this.candidateRepository = candidateRepository;
    }

    public void create(NewCandidateDto newCandidate)
    {
        if (!getCurrentUserDetails().isPresent())
            throw new AccessDeniedException("Need to be logged in");

        var userId = getCurrentUserDetails().get().getUser().getId();
        candidateRepository.create(userId, newCandidate);
    }

    @Override
    public PaginatedList<CandidateDao> findAll(FilteredPageRequest pageRequest)
    {
        return candidateRepository.findAll(pageRequest);
    }

    @Override
    public PaginatedList<CandidateDao> findAllNeedAssigning(FilteredPageRequest pageRequest)
    {
        return candidateRepository.findAllNeedAssigning(pageRequest);
    }

    @Override
    public Optional<CandidateDao> findById(long candidateId)
    {
        return candidateRepository.findById(candidateId);
    }

    private Optional<DefaultUserDetails> getCurrentUserDetails()
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return Optional.of((DefaultUserDetails) authentication.getPrincipal());
        else return Optional.empty();
    }
}

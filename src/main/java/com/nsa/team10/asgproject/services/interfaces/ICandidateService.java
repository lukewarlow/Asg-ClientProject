package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;

public interface ICandidateService
{
    void create(NewCandidateDto newCandidate);
    void sendReceipt(boolean hasPayed);
}

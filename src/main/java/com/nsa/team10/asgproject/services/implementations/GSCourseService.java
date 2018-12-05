package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseLocationDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseTypeDao;
import com.nsa.team10.asgproject.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IGSRepository;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;
import com.nsa.team10.asgproject.services.interfaces.IGSCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GSCourseService implements IGSCourseService
{
    private final IGSRepository gsCourseRepository;
    private final ICandidateRepository candidateRepository;

    @Autowired
    public GSCourseService(IGSRepository gsCourseRepository, ICandidateRepository candidateRepository)
    {
        this.gsCourseRepository = gsCourseRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void create(NewGSCourseDto newGSCourse)
    {
        gsCourseRepository.create(newGSCourse);
    }

    @Override
    public void assignCandidateToCourse(long gsCourseId, long candidateId)
    {
        gsCourseRepository.assignCandidateToCourse(gsCourseId, candidateId);
    }

    @Override
    public PaginatedList<GSCourseDao> findAll(FilteredPageRequest pageRequest)
    {
        return gsCourseRepository.findAll(pageRequest);
    }

    @Override
    public Optional<GSCourseDao> findById(long gsCourseId)
    {
        return gsCourseRepository.findById(gsCourseId);
    }

    @Override
    public PaginatedList<CandidateDao> findAssignedCandidates(long gsCourseId, FilteredPageRequest pageRequest)
    {
        return candidateRepository.findAllAssignedToCourse(gsCourseId, pageRequest);
    }

    @Override
    public PaginatedList<GSCourseTypeDao> findAllTypes(FilteredPageRequest pageRequest)
    {
        return gsCourseRepository.findAllTypes(pageRequest);
    }

    @Override
    public List<GSCourseTypeDao> findAllTypes()
    {
        return gsCourseRepository.findAllTypes();
    }

    @Override
    public PaginatedList<GSCourseLocationDao> findAllLocations(FilteredPageRequest pageRequest)
    {
        return gsCourseRepository.findAllLocations(pageRequest);
    }

    @Override
    public List<GSCourseLocationDao> findAllLocations()
    {
        return gsCourseRepository.findAllLocations();
    }
}

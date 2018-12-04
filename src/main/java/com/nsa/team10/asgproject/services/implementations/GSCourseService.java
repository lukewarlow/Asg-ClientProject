package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.GSCourseDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseLocationDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseTypeDao;
import com.nsa.team10.asgproject.repositories.interfaces.IGSRepository;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;
import com.nsa.team10.asgproject.services.interfaces.IGSCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GSCourseService implements IGSCourseService
{
    private final IGSRepository gsCourseRepository;

    @Autowired
    public GSCourseService(IGSRepository gsCourseRepository)
    {
        this.gsCourseRepository = gsCourseRepository;
    }

    @Override
    public void create(NewGSCourseDto newGSCourse)
    {
        gsCourseRepository.create(newGSCourse);
    }

    @Override
    public PaginatedList<GSCourseDao> findAll(FilteredPageRequest pageRequest)
    {
        return gsCourseRepository.findAll(pageRequest);
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

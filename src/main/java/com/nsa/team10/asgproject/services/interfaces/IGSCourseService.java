package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.GSCourseDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseLocationDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseTypeDao;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;

import java.util.List;

public interface IGSCourseService
{
    void create(NewGSCourseDto newGSCourse);
    PaginatedList<GSCourseDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<GSCourseTypeDao> findAllTypes(FilteredPageRequest pageRequest);
    /*
        For select dropdown on forms in UI. Wouldn't use this in reality but not worth time to do a better selection UI.
     */
    List<GSCourseTypeDao> findAllTypes();
    PaginatedList<GSCourseLocationDao> findAllLocations(FilteredPageRequest pageRequest);
    /*
        For select dropdown on forms in UI. Wouldn't use this in reality but not worth time to do a better selection UI.
     */
    List<GSCourseLocationDao> findAllLocations();
}

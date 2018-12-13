package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseLocationDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseTypeDao;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;

import java.util.List;
import java.util.Optional;

public interface IGSCourseService
{
    void create(NewGSCourseDto newGSCourse);
    void assignCandidateToCourse(long gsCourseId, long candidateId);

    void assignInstructorToCourse(long gsCourseId, long instructorId);

    PaginatedList<GSCourseDao> findAll(FilteredPageRequest pageRequest);
    Optional<GSCourseDao> findById(long gsCourseId);
    PaginatedList<CandidateDao> findAssignedCandidates(long gsCourseId, FilteredPageRequest pageRequest);
    PaginatedList<GSCourseTypeDao> findAllTypes(FilteredPageRequest pageRequest);
    /*
        For select dropdown on forms in UI. Wouldn't use this in reality but not worth time to do a better selection UI.
     */
    List<GSCourseTypeDao> findAllTypes();

    void createLocation(String newLocation);
    PaginatedList<GSCourseLocationDao> findAllLocations(FilteredPageRequest pageRequest);
    /*
        For select dropdown on forms in UI. Wouldn't use this in reality but not worth time to do a better selection UI.
     */
    List<GSCourseLocationDao> findAllLocations();
}

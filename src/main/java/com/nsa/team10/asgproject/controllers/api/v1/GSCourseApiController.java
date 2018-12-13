package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseLocationDao;
import com.nsa.team10.asgproject.repositories.daos.GSCourseTypeDao;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;
import com.nsa.team10.asgproject.services.interfaces.IGSCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gscourses")
public class GSCourseApiController
{
    private final IGSCourseService gsCourseService;

    @Autowired
    public GSCourseApiController(IGSCourseService gsCourseService)
    {
        this.gsCourseService = gsCourseService;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity create(@Valid @RequestBody NewGSCourseDto newGSCourse)
    {
        gsCourseService.create(newGSCourse);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/{courseId:[0-9]+}/candidates/{candidateId:[0-9]+}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity assignCandidateToCourse(@PathVariable long courseId, @PathVariable long candidateId)
    {
        gsCourseService.assignCandidateToCourse(courseId, candidateId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{courseId:[0-9]+}/instructor/{instructorId:[0-9]+}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity assignInstructorToCourse(@PathVariable long courseId, @PathVariable long instructorId)
    {
        gsCourseService.assignInstructorToCourse(courseId, instructorId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<PaginatedList<GSCourseDao>> findAll(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var courses = gsCourseService.findAll(pageRequest);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<GSCourseDao> findById(@PathVariable long id)
    {
        var course = gsCourseService.findById(id);
        if (course.isPresent())
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id:[0-9]+}/candidates")
    public ResponseEntity<PaginatedList<CandidateDao>> findAssignedCandidates(@PathVariable long id, @RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, "");
        var candidates = gsCourseService.findAssignedCandidates(id, pageRequest);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<PaginatedList<GSCourseTypeDao>> findAllTypes(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var types = gsCourseService.findAllTypes(pageRequest);
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/types/all")
    public ResponseEntity<List<GSCourseTypeDao>> findAllTypes()
    {
        var types = gsCourseService.findAllTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/locations")
    public ResponseEntity createNewLocation(@RequestBody String location)
    {
        //I know this is janky, Spring boot / axios is weird with single values, I cba to fight with it so this removes the = from the end of the string.
        var locationString = location.substring(0, location.length() == 0 ? 0 : location.length() - 1);
        gsCourseService.createLocation(locationString);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/locations")
    public ResponseEntity<PaginatedList<GSCourseLocationDao>> findLocations(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var locations = gsCourseService.findAllLocations(pageRequest);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/locations/all")
    public ResponseEntity<List<GSCourseLocationDao>> findAllLocations()
    {
        var locations = gsCourseService.findAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}

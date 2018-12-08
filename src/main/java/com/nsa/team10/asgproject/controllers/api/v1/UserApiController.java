package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.UserDao;
import com.nsa.team10.asgproject.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController
{
    private final IUserService userService;

    @Autowired
    public UserApiController(IUserService userService)
    {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("")
    public ResponseEntity<PaginatedList<UserDao>> findAll(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var list = userService.findAll(pageRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/disabled")
    public ResponseEntity<PaginatedList<UserDao>> findAllDisabled(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var list = userService.findAllDisabled(pageRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/instructors")
    public ResponseEntity<PaginatedList<UserDao>> findAllInstructors(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var list = userService.findAllInstructors(pageRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<UserDao> findById(@PathVariable long id)
    {
        Optional<UserDao> user = userService.findByIdIncDisabled(id);
        return user.map(userDao -> new ResponseEntity<>(userDao, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id:[0-9]+}/disable")
    public ResponseEntity disable(@PathVariable long id)
    {
        var success = userService.disable(id);
        if (success) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id:[0-9]+}/enable")
    public ResponseEntity enable(@PathVariable long id)
    {
        var success = userService.enable(id);
        if (success) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity delete(@PathVariable long id)
    {
        var success = userService.delete(id);
        if (success) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
}

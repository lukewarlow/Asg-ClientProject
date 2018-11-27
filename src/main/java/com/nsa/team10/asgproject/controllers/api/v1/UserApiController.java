package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController
{
    private IUserService userService;

    @Autowired
    public UserApiController(IUserService userService)
    {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("")
    public ResponseEntity<PaginatedList<UserDao>> FindAll(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var list = userService.findAll(pageRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/disabled")
    public ResponseEntity<PaginatedList<UserDao>> FindAllDisabled(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var list = userService.findAllDisabled(pageRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

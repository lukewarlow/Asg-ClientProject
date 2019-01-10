package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.services.interfaces.IOpManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/opmanual")
public class OpManualApiController
{
    private final IOpManualService opManualService;

    @Autowired
    public OpManualApiController(IOpManualService opManualService)
    {
        this.opManualService = opManualService;
    }

    @GetMapping("")
    public ResponseEntity<Resource> downloadTemplate(HttpServletRequest request)
    {
        try
        {
            Path path = Paths.get("ASGResources/OperationsManual.pdf");
            Resource resource = new UrlResource(path.toUri());
            var contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + resource.getFilename() + "\"")
                    .body(resource);
        }
        catch (IOException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('Candidate')")
    public ResponseEntity uploadSubmission(@RequestParam("file") MultipartFile file)
    {
        if (file.isEmpty())
        {
            System.out.print("test");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            try
            {
                var response = opManualService.submit(file);
                if (response.equals("Incorrect filetype"))
                    return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
                return new ResponseEntity(HttpStatus.CREATED);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}


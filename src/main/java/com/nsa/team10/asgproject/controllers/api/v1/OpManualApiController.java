package com.nsa.team10.asgproject.controllers.api.v1;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/opmanual")
public class OpManualApiController
{
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
}


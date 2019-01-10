package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IOpManualRepository;
import com.nsa.team10.asgproject.services.interfaces.IOpManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class OpManualService implements IOpManualService
{
    private final IOpManualRepository opManualRepository;
    private final ICandidateRepository candidateRepository;

    @Autowired
    public OpManualService(IOpManualRepository opManualRepository, ICandidateRepository candidateRepository)
    {
        this.opManualRepository = opManualRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public String submit(MultipartFile file) throws IOException
    {
        byte[] bytes = file.getBytes();
        String[] pathAsArray = file.getOriginalFilename().split("\\\\");
        var filename = pathAsArray[pathAsArray.length - 1];
        if (!filename.toLowerCase().endsWith(".pdf"))
        {
            return "Incorrect filetype";
        }

        var userDetails = AccountService.getCurrentUserDetails();
        if (userDetails.isPresent())
        {
            var email = userDetails.get().getUsername();
            var candidate = candidateRepository.findByEmail(email);
            if (candidate.isPresent())
            {
                var directory = Paths.get("ASGResources/OperationManualSubmissions/" + candidate.get().getId());
                if (Files.notExists(directory))
                    Files.createDirectories(directory);
                var uniqueId = UUID.randomUUID().toString();
                var resource = new UrlResource(directory.toUri() + "/" + uniqueId + "-" + filename);
                Files.write(resource.getFile().toPath(), bytes);

                File uploadedFile = new File(resource.getURI());
                uploadedFile.setReadOnly();

                opManualRepository.submit(candidate.get().getId(), directory.toString() + "/" + uniqueId + "-" + filename);
                return "Success";
            }
            else return "Not candidate";
        }
        else return "Not loggedin";
    }
}

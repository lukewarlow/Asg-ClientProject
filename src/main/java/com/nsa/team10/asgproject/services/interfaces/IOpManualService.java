package com.nsa.team10.asgproject.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IOpManualService
{
    String submit(MultipartFile file) throws IOException;
}

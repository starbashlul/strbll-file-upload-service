package org.example.strbllfileupload.ep.controller;

import org.example.strbllfileupload.bll.service.FileService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

}

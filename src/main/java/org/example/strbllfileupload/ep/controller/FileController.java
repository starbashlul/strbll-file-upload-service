package org.example.strbllfileupload.ep.controller;

import jakarta.validation.constraints.NotNull;
import org.example.strbllfileupload.bll.model.FileMetadata;
import org.example.strbllfileupload.bll.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullFields;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@NotNull @PathVariable UUID id) {
        FileMetadata fileMetadata = fileService.getFileMetadata(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                fileMetadata.getName() +
                                "." +
                                fileMetadata.getFileExtension() +
                                "\""
                )
                .contentType(MediaType.parseMediaType(fileMetadata.getContentType()))
                .body(fileService.download(id));
    }

    @PostMapping
    public ResponseEntity<FileMetadata> saveFile(@NotNull @RequestParam MultipartFile file) {
        FileMetadata result = fileService.save(file);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<FileMetadata> deleteFile(@NotNull @PathVariable UUID id) {
        return fileService.
    }

}

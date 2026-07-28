package org.example.strbllfileupload.ep.controller;

import org.example.strbllfileupload.bll.exception.FileNotFoundException;
import org.example.strbllfileupload.bll.exception.StorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> fileNotFound(
            FileNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<String> storageExceptino(
            StorageException e
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}

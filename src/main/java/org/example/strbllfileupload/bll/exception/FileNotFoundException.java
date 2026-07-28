package org.example.strbllfileupload.bll.exception;

import java.util.UUID;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(UUID id) {
        super("File with id '" + id + "' not found");
    }
}

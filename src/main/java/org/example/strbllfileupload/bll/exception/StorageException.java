package org.example.strbllfileupload.bll.exception;

public class StorageException extends RuntimeException {
    public StorageException(String message, Exception cause) {
        super(message, cause);
    }
}

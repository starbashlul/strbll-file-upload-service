package org.example.strbllfileupload.bll.provider;

import org.example.strbllfileupload.bll.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface StorageProvider {
    void save(MultipartFile file, UUID path) throws IOException;
    Resource download(UUID id) throws IOException;
    void delete(UUID id) throws IOException;
}

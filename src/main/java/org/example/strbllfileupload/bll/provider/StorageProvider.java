package org.example.strbllfileupload.bll.provider;

import org.example.strbllfileupload.bll.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface StorageProvider {
    boolean save(MultipartFile file, UUID path);
    Resource download(UUID id);
    void delete(UUID id);
}

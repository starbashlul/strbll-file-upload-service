package org.example.strbllfileupload.bll.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class StorageProviderDriveImpl implements StorageProvider {

    private final Path rootLocation;


    public StorageProviderDriveImpl(@Value("${storage.root-location}") Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    @Override
    public void save(MultipartFile file, UUID path) throws IOException {
        Files.copy(file.getInputStream(), rootLocation.resolve(path.toString()));
    }

    @Override
    public Resource download(UUID id) throws IOException {
        return new UrlResource(rootLocation.resolve(id.toString()).toUri());
    }

    @Override
    public void delete(UUID id) throws IOException {
        Files.deleteIfExists(rootLocation.resolve(id.toString()));
    }
}

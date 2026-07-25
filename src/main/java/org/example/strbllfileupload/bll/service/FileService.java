package org.example.strbllfileupload.bll.service;

import org.example.strbllfileupload.bll.model.FileMetadata;
import org.example.strbllfileupload.bll.provider.StorageProvider;
import org.example.strbllfileupload.dal.converter.FileMetadataConverter;
import org.example.strbllfileupload.dal.repository.FileMetadataRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class FileService {
    private final FileMetadataRepository fileMetadataRepository;
    private final StorageProvider storageProvider;

    public FileService(FileMetadataRepository fileMetadataRepository, StorageProvider storageProvider) {
        this.fileMetadataRepository = fileMetadataRepository;
        this.storageProvider = storageProvider;
    }

    public FileMetadata save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        UUID id = UUID.randomUUID();

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setType(file.getContentType());
        fileMetadata.setSize(file.getSize());
        fileMetadata.setUploadedAt(OffsetDateTime.now());
        fileMetadata.setId(id);

        try {
            storageProvider.save(file, id);

            fileMetadata = FileMetadataConverter.toModel(fileMetadataRepository.save(FileMetadataConverter.toEntity(fileMetadata)));
        } catch (Exception e) {
            storageProvider.delete(id);
            throw e;
        }

        return fileMetadata;
    }

    public FileMetadata getFileMetadata(UUID id) {
        return FileMetadataConverter.toModel(fileMetadataRepository.findById(id).orElse(null));
    }

    public Resource download(UUID id) {
        fileMetadataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID does not exist"));

        return storageProvider.download(id);
    }
}

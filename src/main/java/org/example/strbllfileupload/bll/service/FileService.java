package org.example.strbllfileupload.bll.service;

import org.example.strbllfileupload.bll.model.FileMetadata;
import org.example.strbllfileupload.bll.provider.StorageProvider;
import org.example.strbllfileupload.dal.converter.FileMetadataConverter;
import org.example.strbllfileupload.dal.repository.FileMetadataRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.Objects;
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

        String originalFilename = file.getOriginalFilename();

        String name = Objects.requireNonNullElse(
                file.getOriginalFilename(),
                "unknown"
        );
        String extension = "";

        if (originalFilename != null) {
            int dotIndex = originalFilename.lastIndexOf('.');

            if (dotIndex > 0) {
                name = originalFilename.substring(0, dotIndex);
                extension = originalFilename.substring(dotIndex + 1);
            }
        }

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setId(id);
        fileMetadata.setName(name);
        fileMetadata.setFileExtension(extension);
        fileMetadata.setContentType(file.getContentType());
        fileMetadata.setSize(file.getSize());
        fileMetadata.setUploadedAt(OffsetDateTime.now());

        try {
            storageProvider.save(file, id);

            fileMetadata = FileMetadataConverter.toModel(fileMetadataRepository.save(FileMetadataConverter.toEntity(fileMetadata)));
        } catch (RuntimeException e) {
            try {
                storageProvider.delete(id);
            } catch (Exception ignored) {
            }

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

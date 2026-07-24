package org.example.strbllfileupload.dal.repository;

import org.example.strbllfileupload.dal.entity.FileMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadataEntity, Integer> {
}

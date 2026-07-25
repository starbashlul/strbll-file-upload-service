package org.example.strbllfileupload.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class FileMetadataEntity {
    @Id
    private UUID id;
    private Long size;
    private String name;
    private String type;
    private OffsetDateTime uploadedAt;

    public FileMetadataEntity() {
        //default
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OffsetDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(OffsetDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileMetadataEntity that)) return false;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "FileMetadataEntity{" +
                "id=" + id +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}

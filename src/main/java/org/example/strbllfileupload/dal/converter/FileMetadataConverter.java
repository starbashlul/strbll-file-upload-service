package org.example.strbllfileupload.dal.converter;

import org.example.strbllfileupload.bll.model.FileMetadata;
import org.example.strbllfileupload.dal.entity.FileMetadataEntity;

public class FileMetadataConverter {
    private FileMetadataConverter() {
        //hide
    }

    public static FileMetadataEntity toEntity(FileMetadata model) {
        if(model == null) return null;

        FileMetadataEntity entity = new FileMetadataEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setSize(model.getSize());
        entity.setType(model.getType());
        entity.setUploadedAt(model.getUploadedAt());
        return entity;
    }

    public static FileMetadata toModel(FileMetadataEntity entity) {
        if(entity == null) return null;

        FileMetadata model = new FileMetadata();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSize(entity.getSize());
        model.setType(entity.getType());
        model.setUploadedAt(entity.getUploadedAt());
        return model;
    }
}

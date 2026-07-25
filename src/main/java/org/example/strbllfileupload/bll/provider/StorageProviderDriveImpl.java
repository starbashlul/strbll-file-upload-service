package org.example.strbllfileupload.bll.provider;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class StorageProviderDriveImpl implements StorageProvider {

    @Override
    public boolean save(MultipartFile file, UUID path) {
        return false;
    }

    @Override
    public Resource download(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }


}

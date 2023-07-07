package com.camersi.camersi.Service.Upload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Upload.UploadDTO;
import com.camersi.camersi.Model.Upload.UploadEntity;
import com.camersi.camersi.Model.Upload.UploadInterface;
import com.camersi.camersi.Service.Result.ResultUploadInterface;

@Service
public class UploadService implements ResultUploadInterface {

    @Autowired
    private UploadInterface upload;

    @Override
    public List<UploadEntity> queryAll() {
        return upload.findAll();
    }

    @Override
    public UploadEntity queryID(Long id) {
        return upload.getReferenceById(id);
    }

    private UploadEntity getEntity(UploadDTO DTO){
        return new UploadEntity(DTO.getId(), DTO.getNombre(), DTO.getFecha());
    }
    
    @Override
    public UploadEntity mutationCreate(UploadDTO entity) {
        return upload.save(getEntity(entity));
    }

    @Override
    public UploadEntity mutationUpdate(UploadDTO entity) {
        UploadEntity uploadID = upload.getReferenceById(entity.getId());
        if (entity.getNombre() != null && entity.getNombre() != uploadID.getNombre())
            uploadID.setNombre(entity.getNombre());
        if (entity.getFecha() != null && entity.getFecha() != uploadID.getFecha())
            uploadID.setFecha(entity.getFecha());

        return upload.save(uploadID);
    }

    @Override
    public Boolean exist(Long id) {
        return upload.existsById(id);
    }
}

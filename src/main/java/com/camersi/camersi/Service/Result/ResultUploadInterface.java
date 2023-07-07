package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Upload.UploadDTO;
import com.camersi.camersi.Model.Upload.UploadEntity;

public interface ResultUploadInterface {
    List<UploadEntity> queryAll();
    UploadEntity queryID(Long id);
    Boolean exist(Long id);
    UploadEntity mutationCreate(UploadDTO entity);
    UploadEntity mutationUpdate(UploadDTO entity);
}

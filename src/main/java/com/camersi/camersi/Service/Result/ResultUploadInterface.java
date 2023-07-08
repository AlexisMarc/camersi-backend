package com.camersi.camersi.Service.Result;

import java.util.List;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.camersi.camersi.Mapping.Upload.UploadDTO;
import com.camersi.camersi.Model.Upload.UploadEntity;

public interface ResultUploadInterface {
    List<UploadEntity> queryAll();
    UploadEntity queryID(Long id);
    Boolean exist(Long id);
    UploadEntity mutationCreate(UploadDTO entity);
    UploadEntity mutationUpdate(UploadDTO entity);

    void delete(String filename);
    UploadEntity save(MultipartFile file);
    Resource load(String filename);
    void deleteAll();
    void deleteFile(String filename);
    Stream<Path> loadAll();
}

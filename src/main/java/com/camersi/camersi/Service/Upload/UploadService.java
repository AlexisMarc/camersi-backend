package com.camersi.camersi.Service.Upload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;

import com.camersi.camersi.Mapping.Upload.UploadDTO;
import com.camersi.camersi.Model.Upload.UploadEntity;
import com.camersi.camersi.Model.Upload.UploadInterface;
import com.camersi.camersi.Service.Result.ResultUploadInterface;

@Service
public class UploadService implements ResultUploadInterface {

    private final Path root = Paths.get("uploads");

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

    private UploadEntity getEntity(UploadDTO DTO) {
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

    @Override
    public UploadEntity save(MultipartFile file) {
        try {
            String fileName = new Date().getTime() + "-" + UUID.randomUUID().toString() + "-file-"
                    + file.getOriginalFilename();
            Path targetLocation = this.root.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);
            return mutationCreate(new UploadDTO(null, fileName, new Date().toString()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void deleteFile(String filename) {
        Path file = root.resolve(filename);
        FileSystemUtils.deleteRecursively(file.toFile());
        delete(filename);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public void delete(String filename) {
        UploadEntity up = upload.findByNombre(filename);
        if (up != null)
            upload.deleteById(up.getId());
    }
}

package com.camersi.camersi.Model.Upload;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadInterface extends JpaRepository<UploadEntity, Long> {
    UploadEntity findByNombre(String nombre);
}

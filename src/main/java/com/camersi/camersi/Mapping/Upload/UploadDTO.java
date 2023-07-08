package com.camersi.camersi.Mapping.Upload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UploadDTO {
    // ID
    private Long id;

    // NOMBRE DEL ARCHIVO
    private String nombre;

    // FECHA DE CREACION
    private String fecha;

    public UploadDTO(Long id, String nombre, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

}

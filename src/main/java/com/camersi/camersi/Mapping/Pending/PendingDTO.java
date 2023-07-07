package com.camersi.camersi.Mapping.Pending;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PendingDTO {
    // ID
    private Long id;

    // FECHA DE CARGADO
    private String fecha;

    // FECHA DE CREACION
    private String creacion;

}

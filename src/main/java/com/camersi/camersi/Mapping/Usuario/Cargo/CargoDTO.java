package com.camersi.camersi.Mapping.Usuario.Cargo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CargoDTO {
    // ID
    private Integer id;

    // NOMBRE DEL CARGO
    private String cargo;

    // DESCRIPCIÓN DE CARGO
    private String descripcion;

    // ESTADO DE ELIMINACION
    private Boolean estado;

}

package com.camersi.camersi.Mapping.Usuario.Cargo;

import com.camersi.camersi.Utils.EnumRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleDTO {
    // ID
    private Integer id;

    // NOMBRE DEL ROL
    private EnumRole role;

}

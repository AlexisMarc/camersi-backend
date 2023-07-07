package com.camersi.camersi.Mapping.Usuario;

import java.util.List;

import com.camersi.camersi.Utils.EnumGenero;
import com.camersi.camersi.Utils.EnumTipoDocumento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
    // ID
    private String id;

    // TIPO DE DOCUMENTO
    private EnumTipoDocumento tipoDocumento;

    // IDENTIFICACIÓN
    private String identificacion;

    // EMAIL
    private String email;

    // NOMBRE DEL USUARIO
    private String nombre;

    // PRIMER APELLIDO
    private String primerApellido;

    // SEGUNDO APELLIDO
    private String segundoApellido;

    // CONTRASEÑA
    private String password;

    // TELEFONO
    private String telefono;

    //GENERO
    private EnumGenero genero;

    // FOTO DE PERFIL
    private String imagen;

    // USUARIO HABILITADO
    private Boolean habilitado;

    // USUARIO ACTIVO
    private Boolean activo;

    // USUARIO BLOQUEADO
    private Boolean bloqueado;

    // CORREO VERFICADO
    private Boolean verificarEmail;

    // CARGO DE USUARIO
    private Integer cargo;

    // ROLES EN EL SISTEMA
    private List<Integer> roles;


}

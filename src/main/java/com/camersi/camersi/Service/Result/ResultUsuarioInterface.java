package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Usuario.UsuarioDTO;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;

public interface ResultUsuarioInterface {
    List<UsuarioEntity> queryAll();
    UsuarioEntity queryID(String id);
    Boolean exist(String id);
    Boolean existEmail(String email);
    Boolean existIdenticacion(String identificacion);
    UsuarioEntity mutationCreate(UsuarioDTO entity);
    UsuarioEntity mutationUpdate(UsuarioDTO entity);
}

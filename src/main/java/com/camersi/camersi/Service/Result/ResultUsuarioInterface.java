package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Public.DtoCreateUsuario;
import com.camersi.camersi.Mapping.Usuario.UsuarioDTO;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;

public interface ResultUsuarioInterface {
    List<UsuarioEntity> queryAll();
    UsuarioEntity queryID(String id);
    Boolean exist(String id);
    Boolean existEmail(String email);
    Boolean existIdenticacion(String identificacion);
    Boolean existId(String id);
    UsuarioEntity queryEmail(String email);
    UsuarioEntity queryIdenticacion(String identificacion);
    UsuarioEntity queryId(String id);
    UsuarioEntity mutationCreate(UsuarioDTO entity);
    UsuarioEntity mutationUpdate(UsuarioDTO entity);
    Boolean resetPassword(String id, String password);
    UsuarioEntity CreatePublic(DtoCreateUsuario entity);
}

package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Usuario.Cargo.RoleDTO;
import com.camersi.camersi.Model.Usuario.Cargo.RoleEntity;
import com.camersi.camersi.Utils.EnumRole;

public interface ResultRoleInterface {
    List<RoleEntity> queryAll();
    RoleEntity queryID(Integer id);
    Boolean exist(Integer id);
    Boolean existRole(EnumRole rol);
    List<RoleEntity> getRoles(Iterable<Integer> ids);
    RoleEntity mutationCreate(RoleDTO entity);
    RoleEntity mutationUpdate(RoleDTO entity);
}

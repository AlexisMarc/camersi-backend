package com.camersi.camersi.Service.Usuario.Cargo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Usuario.Cargo.RoleDTO;
import com.camersi.camersi.Model.Usuario.Cargo.RoleEntity;
import com.camersi.camersi.Model.Usuario.Cargo.RoleInterface;
import com.camersi.camersi.Service.Result.ResultRoleInterface;
import com.camersi.camersi.Utils.EnumRole;

@Service
public class RoleService implements ResultRoleInterface {

    @Autowired
    private RoleInterface role;

    @Override
    public List<RoleEntity> queryAll() {
        return role.findAll();
    }

    @Override
    public RoleEntity queryID(Integer id) {
        return role.getReferenceById(id);
    }

    private RoleEntity getEntity(RoleDTO DTO){
        return new RoleEntity(DTO.getId(), DTO.getRole(), null);
    }

    @Override
    public RoleEntity mutationCreate(RoleDTO entity) {
        return role.save(getEntity(entity));
    }

    @Override
    public RoleEntity mutationUpdate(RoleDTO entity) {
        RoleEntity roleID = role.getReferenceById(entity.getId());

        if (entity.getRole() != null && entity.getRole() != roleID.getRole())
            roleID.setRole(entity.getRole());

        return role.save(roleID);
    }

    @Override
    public Boolean exist(Integer id) {
        return role.existsById(id);
    }

    @Override
    public Boolean existRole(EnumRole rol) {
        if (role.findByRole(rol) == null)
            return false;
        return true;
    }

    @Override
    public List<RoleEntity> getRoles(Iterable<Integer> ids) {
        return role.findAllById(ids);
    }

}

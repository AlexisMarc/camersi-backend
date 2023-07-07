package com.camersi.camersi.Model.Usuario.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.camersi.camersi.Utils.EnumRole;


public interface RoleInterface extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(EnumRole role);
}

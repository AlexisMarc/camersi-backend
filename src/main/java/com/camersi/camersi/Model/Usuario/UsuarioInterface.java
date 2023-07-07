package com.camersi.camersi.Model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioInterface  extends JpaRepository<UsuarioEntity, String>{
    UsuarioEntity findByEmail(String email);
    UsuarioEntity findByIdentificacion(String identificacion);
}

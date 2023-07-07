package com.camersi.camersi.Model.Usuario.Cargo;

import java.util.List;

import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Utils.EnumRole;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Setter
@Getter
@NoArgsConstructor
public class RoleEntity {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // NOMBRE DEL ROL
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EnumRole role;

    // USUARIOS CON EL ROL
    @ManyToMany(mappedBy = "roles")
    @JsonBackReference(value = "usuario_role")
    private List<UsuarioEntity> usuarios;

    public RoleEntity(Integer id, EnumRole role, List<UsuarioEntity> usuarios) {
        this.id = id;
        this.role = role;
        this.usuarios = usuarios;
    }

}

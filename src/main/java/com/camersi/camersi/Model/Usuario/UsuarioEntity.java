package com.camersi.camersi.Model.Usuario;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.camersi.camersi.Model.Typified.TypifiedEntity;
import com.camersi.camersi.Model.Usuario.Cargo.CargoEntity;
import com.camersi.camersi.Model.Usuario.Cargo.RoleEntity;
import com.camersi.camersi.Utils.EnumGenero;
import com.camersi.camersi.Utils.EnumTipoDocumento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"identificacion", "email"}))
public class UsuarioEntity {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false)
    private String id;

    // TIPO DE DOCUMENTO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumTipoDocumento tipoDocumento;

    // IDENTIFICACIÓN
    @Column(nullable = false, unique = true, length = 15, name = "identificacion")
    private String identificacion;

    // EMAIL
    @Column(nullable = false, unique = true, name = "email")
    @NaturalId
    private String email;

    // NOMBRE DEL USUARIO
    @Column(nullable = false, length = 60)
    private String nombre;

    // PRIMER APELLIDO
    @Column(nullable = false, length = 30)
    private String primerApellido;

    // SEGUNDO APELLIDO
    @Column(nullable = true, length = 30)
    private String segundoApellido;

    // CONTRASEÑA
    @Column(nullable = false)
    private String password;

    // TELEFONO
    @Column(nullable = true)
    private String telefono;

    //GENERO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumGenero genero;

    // FOTO DE PERFIL
    @Column(nullable = true)
    private String imagen;

    // USUARIO HABILITADO
    @Column(nullable = false)
    private Boolean habilitado;

    // USUARIO ACTIVO
    @Column(nullable = false)
    private Boolean activo;

    // USUARIO BLOQUEADO
    @Column(nullable = false)
    private Boolean bloqueado;

    // CORREO VERFICADO
    @Column(nullable = false)
    private Boolean verificarEmail;

    // CARGO DE USUARIO
    @ManyToOne(optional = true)
    @JsonManagedReference(value = "usuario_cargo")
    private CargoEntity cargo;

    // ROLES EN EL SISTEMA
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference(value = "usuario_role")
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    private List<RoleEntity> roles;

    // TIPIFICADOS REALIZADOS POR EL AGENTE
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agente")
    @JsonBackReference(value = "usuario_typified")
    private List<TypifiedEntity> typified;

    public UsuarioEntity(String id, EnumTipoDocumento tipoDocumento, String identificacion, String email, String nombre,
            String primerApellido, String segundoApellido, String password, String telefono, EnumGenero genero,
            String imagen, Boolean habilitado, Boolean activo, Boolean bloqueado, Boolean verificarEmail,
            CargoEntity cargo, List<RoleEntity> roles, List<TypifiedEntity> typified) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.genero = genero;
        this.imagen = imagen;
        this.habilitado = habilitado;
        this.activo = activo;
        this.bloqueado = bloqueado;
        this.verificarEmail = verificarEmail;
        this.cargo = cargo;
        this.roles = roles;
        this.typified = typified;
    }

    


}

package com.camersi.camersi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.camersi.camersi.Mapping.Pending.PendingDTO;
import com.camersi.camersi.Mapping.Typified.TypifiedDTO;
import com.camersi.camersi.Mapping.Upload.UploadDTO;
import com.camersi.camersi.Mapping.Usuario.UsuarioDTO;
import com.camersi.camersi.Mapping.Usuario.Cargo.CargoDTO;
import com.camersi.camersi.Mapping.Usuario.Cargo.RoleDTO;
import com.camersi.camersi.Model.Pending.PendingEntity;
import com.camersi.camersi.Model.Typified.TypifiedEntity;
import com.camersi.camersi.Model.Upload.UploadEntity;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Model.Usuario.Cargo.CargoEntity;
import com.camersi.camersi.Model.Usuario.Cargo.RoleEntity;
import com.camersi.camersi.Service.Pending.PendingService;
import com.camersi.camersi.Service.Typified.TypifiedService;
import com.camersi.camersi.Service.Upload.UploadService;
import com.camersi.camersi.Service.Usuario.UsuarioService;
import com.camersi.camersi.Service.Usuario.Cargo.CargoService;
import com.camersi.camersi.Service.Usuario.Cargo.RoleService;
import com.camersi.camersi.Utils.EnumRole;

@Controller
public class ControllerGraphql {

    @Autowired
    private TypifiedService typified;

    @Autowired
    private UsuarioService usuario;

    @Autowired
    private PendingService pending;

    @Autowired
    private UploadService upload;

    @Autowired
    private RoleService role;

    @Autowired
    private CargoService cargo;

    //QUERY ALL

    @QueryMapping
    public List<TypifiedEntity> queryTypified() {
        return typified.queryAll();
    }
    @QueryMapping
    public List<UsuarioEntity> queryUsuario() {
        return usuario.queryAll();
    }
    @QueryMapping
    public List<UploadEntity> queryUpload() {
        return upload.queryAll();
    }
    @QueryMapping
    public List<PendingEntity> queryPending() {
        return pending.queryAll();
    }
    @QueryMapping
    public List<RoleEntity> queryRole() {
        return role.queryAll();
    }
    @QueryMapping
    public List<CargoEntity> queryCargo() {
        return cargo.queryAll();
    }

    //QUERY ID

    @QueryMapping
    public TypifiedEntity queryIDTypified(@Argument Long id) {
        return typified.queryID(id);
    }
    @QueryMapping
    public UsuarioEntity queryIDUsuario(@Argument String id) {
        return usuario.queryID(id);
    }
    @QueryMapping
    public UploadEntity queryIDUpload(@Argument Long id) {
        return upload.queryID(id);
    }
    @QueryMapping
    public PendingEntity queryIDPending(@Argument Long id) {
        return pending.queryID(id);
    }
    @QueryMapping
    public RoleEntity queryIDRole(@Argument Integer id) {
        return role.queryID(id);
    }
    @QueryMapping
    public CargoEntity queryIDCargo(@Argument Integer id) {
        return cargo.queryID(id);
    }

    //QUERY EXIST

    @QueryMapping
    public Boolean queryExistTypified(@Argument Long id) {
        return typified.exist(id);
    }
    @QueryMapping
    public Boolean queryExistUsuario(@Argument String id) {
        return usuario.exist(id);
    }
    @QueryMapping
    public Boolean queryExistUpload(@Argument Long id) {
        return upload.exist(id);
    }
    @QueryMapping
    public Boolean queryExistPending(@Argument Long id) {
        return pending.exist(id);
    }
    @QueryMapping
    public Boolean queryExistRole(@Argument Integer id) {
        return role.exist(id);
    }
    @QueryMapping
    public Boolean queryExistCargo(@Argument Integer id) {
        return cargo.exist(id);
    }


    //QUERY EXIST ESPECIAL

    @QueryMapping
    public Boolean queryExistEmailUsuario(@Argument String email) {
        return usuario.existEmail(email);
    }
    @QueryMapping
    public Boolean queryExistIdentificacionUsuario(@Argument String identificacion) {
        return usuario.existIdenticacion(identificacion);
    }
    @QueryMapping
    public Boolean queryExistFechaPending(@Argument String fecha) {
        return pending.existFecha(fecha);
    }
    @QueryMapping
    public Boolean queryExistRoleRole(@Argument EnumRole rol) {
        return role.existRole(rol);
    }
    @QueryMapping
    public Boolean queryExistCargoCargo(@Argument String carg) {
        return cargo.existCargo(carg);
    }

    //MUTATION CREATE

    @MutationMapping
    public TypifiedEntity mutationCreateTypified(@Argument TypifiedDTO entity) {
        return typified.mutationCreate(entity);
    }
    @MutationMapping
    public UsuarioEntity mutationCreateUsuario(@Argument UsuarioDTO entity) {
        return usuario.mutationCreate(entity);
    }
    @MutationMapping
    public UploadEntity mutationCreateUpload(@Argument UploadDTO entity) {
        return upload.mutationCreate(entity);
    }
    @MutationMapping
    public PendingEntity mutationCreatePending(@Argument PendingDTO entity) {
        return pending.mutationCreate(entity);
    }
    @MutationMapping
    public RoleEntity mutationCreateRole(@Argument RoleDTO entity) {
        return role.mutationCreate(entity);
    }
    @MutationMapping
    public CargoEntity mutationCreateCargo(@Argument CargoDTO entity) {
        return cargo.mutationCreate(entity);
    }

    //MUTATION UPDATE

    @MutationMapping
    public TypifiedEntity mutationUpdateTypified(@Argument TypifiedDTO entity) {
        return typified.mutationUpdate(entity);
    }
    @MutationMapping
    public UsuarioEntity mutationUpdateUsuario(@Argument UsuarioDTO entity) {
        return usuario.mutationUpdate(entity);
    }
    @MutationMapping
    public UploadEntity mutationUpdateUpload(@Argument UploadDTO entity) {
        return upload.mutationUpdate(entity);
    }
    @MutationMapping
    public PendingEntity mutationUpdatePending(@Argument PendingDTO entity) {
        return pending.mutationUpdate(entity);
    }
    @MutationMapping
    public RoleEntity mutationUpdateRole(@Argument RoleDTO entity) {
        return role.mutationUpdate(entity);
    }
    @MutationMapping
    public CargoEntity mutationUpdateCargo(@Argument CargoDTO entity) {
        return cargo.mutationUpdate(entity);
    }
}

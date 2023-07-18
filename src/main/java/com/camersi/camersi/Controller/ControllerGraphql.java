package com.camersi.camersi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/graphql")
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
    @GetMapping("/queryTypified")
    public List<TypifiedEntity> queryTypified() {
        return typified.queryAll();
    }
    @GetMapping("/queryUsuario")
    public List<UsuarioEntity> queryUsuario() {
        return usuario.queryAll();
    }
    @GetMapping("/queryUpload")
    public List<UploadEntity> queryUpload() {
        return upload.queryAll();
    }
    @GetMapping("/queryPending")
    public List<PendingEntity> queryPending() {
        return pending.queryAll();
    }
    @GetMapping("/queryRole")
    public List<RoleEntity> queryRole() {
        return role.queryAll();
    }
    @GetMapping("/queryCargo")
    public List<CargoEntity> queryCargo() {
        return cargo.queryAll();
    }

    //QUERY ID

    @GetMapping("/queryIDTypified")
    public TypifiedEntity queryIDTypified(@RequestBody Long id) {
        return typified.queryID(id);
    }
    @GetMapping("/queryIDUsuario")
    public UsuarioEntity queryIDUsuario(@RequestBody String id) {
        return usuario.queryID(id);
    }
    @GetMapping("/queryIDUpload")
    public UploadEntity queryIDUpload(@RequestBody Long id) {
        return upload.queryID(id);
    }
    @GetMapping("/queryIDPending")
    public PendingEntity queryIDPending(@RequestBody Long id) {
        return pending.queryID(id);
    }
    @GetMapping("/queryIDRole")
    public RoleEntity queryIDRole(@RequestBody Integer id) {
        return role.queryID(id);
    }
    @GetMapping("/queryIDCargo")
    public CargoEntity queryIDCargo(@RequestBody Integer id) {
        return cargo.queryID(id);
    }

    //QUERY EXIST

    @GetMapping("/queryExistTypified")
    public Boolean queryExistTypified(@RequestBody Long id) {
        return typified.exist(id);
    }
    @GetMapping("/queryExistUsuario")
    public Boolean queryExistUsuario(@RequestBody String id) {
        return usuario.exist(id);
    }
    @GetMapping("/queryExistUpload")
    public Boolean queryExistUpload(@RequestBody Long id) {
        return upload.exist(id);
    }
    @GetMapping("/queryExistPending")
    public Boolean queryExistPending(@RequestBody Long id) {
        return pending.exist(id);
    }
    @GetMapping("/queryExistRole")
    public Boolean queryExistRole(@RequestBody Integer id) {
        return role.exist(id);
    }
    @GetMapping("/queryExistCargo")
    public Boolean queryExistCargo(@RequestBody Integer id) {
        return cargo.exist(id);
    }


    //QUERY EXIST ESPECIAL

    @GetMapping("/queryExistEmailUsuario")
    public Boolean queryExistEmailUsuario(@RequestBody String email) {
        return usuario.existEmail(email);
    }
    @GetMapping("/queryExistIdentificacionUsuario")
    public Boolean queryExistIdentificacionUsuario(@RequestBody String identificacion) {
        return usuario.existIdenticacion(identificacion);
    }
    @GetMapping("/queryExistFechaPending")
    public Boolean queryExistFechaPending(@RequestBody String fecha) {
        return pending.existFecha(fecha);
    }
    @GetMapping("/queryExistRoleRole")
    public Boolean queryExistRoleRole(@RequestBody EnumRole rol) {
        return role.existRole(rol);
    }
    @GetMapping("/queryExistCargoCargo")
    public Boolean queryExistCargoCargo(@RequestBody String carg) {
        return cargo.existCargo(carg);
    }

    //MUTATION CREATE

    @PostMapping("/mutationCreateTypified")
    public TypifiedEntity mutationCreateTypified(@RequestBody TypifiedDTO entity) {
        return typified.mutationCreate(entity);
    }
    @PostMapping("/mutationCreateUsuario")
    public UsuarioEntity mutationCreateUsuario(@RequestBody UsuarioDTO entity) {
        return usuario.mutationCreate(entity);
    }
    @PostMapping("/mutationCreateUpload")
    public UploadEntity mutationCreateUpload(@RequestBody UploadDTO entity) {
        return upload.mutationCreate(entity);
    }
    @PostMapping("/mutationCreatePending")
    public PendingEntity mutationCreatePending(@RequestBody PendingDTO entity) {
        return pending.mutationCreate(entity);
    }
    @PostMapping("/mutationCreateRole")
    public RoleEntity mutationCreateRole(@RequestBody RoleDTO entity) {
        return role.mutationCreate(entity);
    }
    @PostMapping("/mutationCreateCargo")
    public CargoEntity mutationCreateCargo(@RequestBody CargoDTO entity) {
        return cargo.mutationCreate(entity);
    }

    //MUTATION UPDATE

    @PostMapping("/mutationUpdateTypified")
    public TypifiedEntity mutationUpdateTypified(@RequestBody TypifiedDTO entity) {
        return typified.mutationUpdate(entity);
    }
    @PostMapping("/mutationUpdateUsuario")
    public UsuarioEntity mutationUpdateUsuario(@RequestBody UsuarioDTO entity) {
        return usuario.mutationUpdate(entity);
    }
    @PostMapping("/mutationUpdateUpload")
    public UploadEntity mutationUpdateUpload(@RequestBody UploadDTO entity) {
        return upload.mutationUpdate(entity);
    }
    @PostMapping("/mutationUpdatePending")
    public PendingEntity mutationUpdatePending(@RequestBody PendingDTO entity) {
        return pending.mutationUpdate(entity);
    }
    @PostMapping("/mutationUpdateRole")
    public RoleEntity mutationUpdateRole(@RequestBody RoleDTO entity) {
        return role.mutationUpdate(entity);
    }
    @PostMapping("/mutationUpdateCargo")
    public CargoEntity mutationUpdateCargo(@RequestBody CargoDTO entity) {
        return cargo.mutationUpdate(entity);
    }
}

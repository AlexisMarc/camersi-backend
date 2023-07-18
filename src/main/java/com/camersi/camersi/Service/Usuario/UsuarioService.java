package com.camersi.camersi.Service.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Public.DtoCreateUsuario;
import com.camersi.camersi.Mapping.Usuario.UsuarioDTO;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Model.Usuario.UsuarioInterface;
import com.camersi.camersi.Model.Usuario.Cargo.CargoEntity;
import com.camersi.camersi.Model.Usuario.Cargo.RoleEntity;
import com.camersi.camersi.Service.Result.ResultUsuarioInterface;
import com.camersi.camersi.Service.Usuario.Cargo.CargoService;
import com.camersi.camersi.Service.Usuario.Cargo.RoleService;

@Service
public class UsuarioService implements ResultUsuarioInterface {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UsuarioInterface usuario;

    @Autowired
    private RoleService role;

    @Autowired
    private CargoService cargo;

    @Override
    public List<UsuarioEntity> queryAll() {
        return usuario.findAll();
    }

    @Override
    public UsuarioEntity queryID(String id) {
        return usuario.getReferenceById(id);
    }

    private UsuarioEntity getEntity(UsuarioDTO DTO) {
        return new UsuarioEntity(DTO.getId(), DTO.getTipoDocumento(), DTO.getIdentificacion(), DTO.getEmail(),
                DTO.getNombre(), DTO.getPrimerApellido(), DTO.getSegundoApellido(), passwordEncoder.encode(DTO.getPassword()),
                DTO.getTelefono(), DTO.getGenero(), DTO.getImagen(), false, false, false, false,
                getCargo(DTO.getCargo()), getRoles(DTO.getRoles()), null);
    }

    @Override
    public UsuarioEntity mutationCreate(UsuarioDTO entity) {
        return usuario.save(getEntity(entity));
    }

    @Override
    public UsuarioEntity mutationUpdate(UsuarioDTO entity) {

        UsuarioEntity usuarioID = usuario.getReferenceById(entity.getId());

        if (entity.getTipoDocumento() != null && entity.getTipoDocumento() != usuarioID.getTipoDocumento())
            usuarioID.setTipoDocumento(entity.getTipoDocumento());
        if (entity.getIdentificacion() != null && entity.getIdentificacion() != usuarioID.getIdentificacion())
            usuarioID.setIdentificacion(entity.getIdentificacion());
        if (entity.getEmail() != null && entity.getEmail() != usuarioID.getEmail())
            usuarioID.setEmail(entity.getEmail());
        if (entity.getNombre() != null && entity.getNombre() != usuarioID.getNombre())
            usuarioID.setNombre(entity.getNombre());
        if (entity.getPrimerApellido() != null && entity.getPrimerApellido() != usuarioID.getPrimerApellido())
            usuarioID.setPrimerApellido(entity.getPrimerApellido());
        if (entity.getSegundoApellido() != null && entity.getSegundoApellido() != usuarioID.getSegundoApellido())
            usuarioID.setSegundoApellido(entity.getSegundoApellido());
        if (entity.getPassword() != null && entity.getPassword() != usuarioID.getPassword())
            usuarioID.setPassword(entity.getPassword());
        if (entity.getTelefono() != null && entity.getTelefono() != usuarioID.getTelefono())
            usuarioID.setTelefono(entity.getTelefono());
        if (entity.getGenero() != null && entity.getGenero() != usuarioID.getGenero())
            usuarioID.setGenero(entity.getGenero());
        if (entity.getImagen() != null && entity.getImagen() != usuarioID.getImagen())
            usuarioID.setImagen(entity.getImagen());
        if (entity.getHabilitado() != null && entity.getHabilitado() != usuarioID.getHabilitado())
            usuarioID.setHabilitado(entity.getHabilitado());
        if (entity.getActivo() != null && entity.getActivo() != usuarioID.getActivo())
            usuarioID.setActivo(entity.getActivo());
        if (entity.getBloqueado() != null && entity.getBloqueado() != usuarioID.getBloqueado())
            usuarioID.setBloqueado(entity.getBloqueado());
        if (entity.getCargo() != null)
            usuarioID.setCargo(getCargo(entity.getCargo()));
        if (entity.getRoles() != null)
            usuarioID.setRoles(getRoles(entity.getRoles()));

        return usuario.save(usuarioID);
    }

    @Override
    public Boolean exist(String id) {
        return usuario.existsById(id);
    }

    @Override
    public Boolean existEmail(String email) {
        if (usuario.findByEmail(email) == null)
            return false;
        return true;
    }

    @Override
    public Boolean existIdenticacion(String identificacion) {
        if (usuario.findByIdentificacion(identificacion) == null)
            return false;
        return true;
    }

    private List<RoleEntity> getRoles(List<Integer> rol) {
        if (rol == null)
            return null;
        return role.getRoles(rol);
    }

    private CargoEntity getCargo(Integer id) {
        if (id == null)
            return null;
        if (!cargo.exist(id))
            return null;
        return cargo.queryID(id);
    }

    @Override
    public UsuarioEntity queryEmail(String email) {
        return usuario.findByEmail(email);
    }

    @Override
    public UsuarioEntity queryIdenticacion(String identificacion) {
        return usuario.findByIdentificacion(identificacion);
    }

    @Override
    public Boolean existId(String id) {
        if (usuario.getReferenceById(id) == null)
            return false;
        return true;
    }

    @Override
    public UsuarioEntity queryId(String id) {
        return usuario.getReferenceById(id);
    }

    @Override
    public Boolean resetPassword(String id, String password) {
        try {
            UsuarioEntity user = usuario.getReferenceById(id);
            user.setPassword(passwordEncoder.encode(password));
            usuario.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UsuarioEntity CreatePublic(DtoCreateUsuario entity) {
        return usuario.save(createUsuario(entity));
    }

    // DE CREATE A ENTITY
    private UsuarioEntity createUsuario(DtoCreateUsuario DTO) {
        return new UsuarioEntity(null, DTO.getTipoDocumento(), DTO.getIdentificacion(), DTO.getEmail(),
                DTO.getNombre(), DTO.getPrimerApellido(), DTO.getSegundoApellido(), passwordEncoder.encode(DTO.getPassword()),
                DTO.getTelefono(), DTO.getGenero(), null, false, false, false, false,
                null, null, null);
    }
}

package com.camersi.camersi.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Model.Usuario.Cargo.RoleEntity;
import com.camersi.camersi.Service.Usuario.UsuarioService;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioService interfaceUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = interfaceUsuario.queryEmail(username);
        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getHabilitado(), usuario.getActivo(),
                usuario.getActivo(), usuario.getBloqueado(), getAuthorities(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<RoleEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            String name = role.getRole().toString().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

}

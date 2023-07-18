package com.camersi.camersi.Service.Auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Public.DtoJwt;
import com.camersi.camersi.Mapping.Public.DtoLogin;
import com.camersi.camersi.Mapping.Public.DtoNewPassword;
import com.camersi.camersi.Mapping.Public.DtoPassword;
import com.camersi.camersi.Mapping.Public.TokenDto;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Security.jwt.JwtProvider;
import com.camersi.camersi.Service.Email.EnvioEmail;
import com.camersi.camersi.Service.Usuario.UsuarioService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@Service
public class ImpAuthService implements AuthService {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private UsuarioService interfaceUsuario;

    @Autowired
    private EnvioEmail EnvioEmail;

    @Autowired
    private JwtProvider jwtProvider;

    private PasswordEncoder password = new BCryptPasswordEncoder();

    //////////////////////////////////////////////////////////////////////////////////////////////
    // <---CONTRASEÑA---->//
    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String[] oblivionPassword(DtoPassword dto) throws AddressException, MessagingException {
        Integer PIN = getNumberPin();
        String correo = dto.getEmail();
        String identificacion = dto.getIdentificacion();
        if (!(correo == null)) {
            UsuarioEntity user = interfaceUsuario.queryEmail(dto.getEmail());
            String email = user.getEmail();
            EnvioEmail.sendEmailPin(email, PIN.toString());
            String respuesta = email.substring(0, email.indexOf("@") - 3).replaceAll("[a-z]", "*")
                    + email.substring(email.indexOf("@") - 3, email.length());
            String data[] = { password.encode(PIN.toString()), respuesta, new Date().toString(), user.getId() };
            return data;
        } else if (!(identificacion == null)) {
            UsuarioEntity user = interfaceUsuario.queryIdenticacion(dto.getIdentificacion());
            String email = user.getEmail();
            EnvioEmail.sendEmailPin(email, PIN.toString());
            String respuesta = email.substring(0, email.indexOf("@") - 3).replaceAll("[a-z]", "*")
                    + email.substring(email.indexOf("@") - 3, email.length());
            String data[] = { password.encode(PIN.toString()), respuesta, new Date().toString(), user.getId() };
            return data;
        }
        return null;
    }

    private Integer getNumberPin() {
        double fiveDigits = 100000 + Math.random() * 900000;
        Integer PIN = (int) fiveDigits;
        return PIN;
    }

    @Override
    public Boolean newPassword(DtoNewPassword dto) {
        if (password.matches(dto.getPin(), dto.getKey())) {
            return interfaceUsuario.resetPassword(dto.getId(), dto.getPassword());
        }
        return false;
    }

    @Override
    public String validMail(String email) {
        Integer PIN = getNumberPin();
        try {
            EnvioEmail.sendEmailPin(email, PIN.toString());
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return password.encode(PIN.toString());
    }

    @Override
    public boolean validMailKey(String[] valide) {
        if (!(valide[0] == null) && !(valide[1] == null)) {
            if (password.matches(valide[0], valide[1])) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public TokenDto validateToken(String token) {
        if (!jwtProvider.validateToken(token)) // FALTA RUTA!
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if (interfaceUsuario.queryEmail(username) == null)
            return null;
        return new TokenDto(token);
    }

    @Override
    public DtoJwt login(DtoLogin login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(), login.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        Date vence = jwtProvider.getExpirationFromToken(jwt);
        String id = jwtProvider.getUserIdFromToken(jwt);
        Object role = jwtProvider.getRolesFromToken(jwt);

        DtoJwt jwtDto = new DtoJwt(jwt, id, vence, role);

        return jwtDto;
    }
}
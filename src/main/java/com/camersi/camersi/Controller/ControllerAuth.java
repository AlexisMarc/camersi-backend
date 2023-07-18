package com.camersi.camersi.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.camersi.camersi.Mapping.Public.DtoCreateUsuario;
import com.camersi.camersi.Mapping.Public.DtoEmail;
import com.camersi.camersi.Mapping.Public.DtoJwt;
import com.camersi.camersi.Mapping.Public.DtoLogin;
import com.camersi.camersi.Mapping.Public.DtoNewPassword;
import com.camersi.camersi.Mapping.Public.DtoPassword;
import com.camersi.camersi.Mapping.Public.TokenDto;
import com.camersi.camersi.Messages.Global.MessageDetails;
import com.camersi.camersi.Service.Auth.ImpAuthService;
import com.camersi.camersi.Service.Usuario.UsuarioService;
import com.camersi.camersi.Utils.EnumOperacion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {
    // INYECCIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO USUARIO
    @Autowired
    private UsuarioService impUsuario;

    // INYECCIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO DE AUTENTICACIÓN
    @Autowired
    private ImpAuthService impAuth;

    // LOGIN
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody DtoLogin login) {
        try {
            DtoJwt jwtDto = impAuth.login(login);
            return ResponseEntity.ok().body(jwtDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.LOGIN, new Date(),
                            "Inicio de sesión fallido", e.getMessage()));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        TokenDto tokenDto = impAuth.validateToken(token);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    // ENVIO DE CORREO PARA RESTABLECER CONTRASEÑA
    @PostMapping(value = "/password")
    public ResponseEntity<?> password(@RequestBody DtoPassword dtoPassword) {
        try {
            String[] respuesta = impAuth.oblivionPassword(dtoPassword);
            if (respuesta == null)
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                                "Usuario no encontrado", "Los datos enviados no se encuentran"));
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Correo no enviado", e.getMessage()));
        }
    }

    // NUEVA CONTRASEÑA
    @PostMapping(value = "/password/new")
    public ResponseEntity<?> passwordNew(@Valid @RequestBody DtoNewPassword dtoPassword) {
        try {
            boolean respuesta = impAuth.newPassword(dtoPassword);
            if (!respuesta)
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.UNAUTHORIZED, EnumOperacion.MODIFICAR, new Date(),
                                "Fallo en cambio de contraseña", "Los datos no coinciden"));
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.MODIFICAR, new Date(),
                            "Error en cambio de contraseña", e.getMessage()));
        }
    }

    // GUARDAR USUARIO
    @PostMapping("/register")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody DtoCreateUsuario DTO) {
        try {
            if (impUsuario.existEmail(DTO.getEmail()) || impUsuario.existIdenticacion(DTO.getIdentificacion())) {
                return ResponseEntity.accepted().body(new MessageDetails(HttpStatus.ACCEPTED, EnumOperacion.GUARDAR,
                        new Date(), "Usuario existente", null));
            } else {
                return ResponseEntity.ok(impUsuario.CreatePublic(DTO));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Usuario no registrado", e.getMessage()));
        }

    }

    // ENVIAR VALIDACIÓN EMAIL
    @PostMapping(value = "/email")
    public ResponseEntity<?> valideEmail(@NotNull @RequestBody DtoEmail email) {
        try {
            return ResponseEntity.ok().body(impAuth.validMail(email.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Correo no enviado", e.getMessage()));
        }
    }

    // VALIDAR EMAIL
    @PostMapping(value = "/email/valide")
    public ResponseEntity<?> valideEmailKey(@NotNull @RequestBody String[] valide) {
        try {
            return ResponseEntity.ok().body(impAuth.validMailKey(valide));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Correo no enviado", e.getMessage()));
        }
    }

}
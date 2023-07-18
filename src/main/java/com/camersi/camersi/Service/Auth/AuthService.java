package com.camersi.camersi.Service.Auth;

import com.camersi.camersi.Mapping.Public.DtoJwt;
import com.camersi.camersi.Mapping.Public.DtoLogin;
import com.camersi.camersi.Mapping.Public.DtoNewPassword;
import com.camersi.camersi.Mapping.Public.DtoPassword;
import com.camersi.camersi.Mapping.Public.TokenDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public interface AuthService {
    String[] oblivionPassword(DtoPassword dto) throws AddressException, MessagingException; //RECUPERAR CONTRASEÑA

    Boolean newPassword(DtoNewPassword dto); //NUEVA CONTRASEÑA

    String validMail(String email); //VALIDAR EMAIL

    boolean validMailKey(String[] valide); //VALIDAR KEY Y PIN

    public DtoJwt login(DtoLogin login); // LOGIN

    public TokenDto validateToken(String token); // VALIDAR TOKEN
}

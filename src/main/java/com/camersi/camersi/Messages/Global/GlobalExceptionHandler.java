package com.camersi.camersi.Messages.Global;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.relation.RelationNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.camersi.camersi.Utils.EnumOperacion;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> NotValidException(MethodArgumentNotValidException ex, Exception exception,
            WebRequest webRequest) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();

            errores.put(nombreCampo, mensaje);
        });
        MessageDetails MessageDetails = new MessageDetails(HttpStatus.UNPROCESSABLE_ENTITY, EnumOperacion.VALIDACION,
                new Date(),
                "Datos no validos", errores);

        return new ResponseEntity<>(MessageDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

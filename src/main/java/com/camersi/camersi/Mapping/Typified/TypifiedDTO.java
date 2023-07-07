package com.camersi.camersi.Mapping.Typified;

import com.camersi.camersi.Utils.EnumCategoria;
import com.camersi.camersi.Utils.EnumCertificado;
import com.camersi.camersi.Utils.EnumSoporte;
import com.camersi.camersi.Utils.EnumSubSoporte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TypifiedDTO {
    // ID
    private Long id;
    // DOCUMENTO DEL TITULAR DE LA FIRMA
    private String identidad;
    // NOMBRE DEL TITULAR DE LA FIRMA
    private String nombre;
    // TELEFONO DEL TITULAR DE LA FIRMA
    private String telefono;
    // COMPAÑIA DEL TITULAR DE LA FIRMA
    private String company;
    // EMAIL DEL TITULAR DE LA FIRMA
    private String email;
    // TIPO DE CERTIFICADO EMITIDO
    private EnumCertificado certificado;
    // TIPO DE SOPORTE REALIZADO
    private EnumSoporte soporte;
    // SUB TIPO DE SOPORTE
    private EnumSubSoporte subSoporte;
    // FECHA DEL SOPORTE
    private String fecha;
    // HORA DEL SOPORTE
    private String hora;
    // OBSERVACIONES DEL SOPORTER
    private String observacion;
    // ESTADO DEL SOPORTE
    private Boolean estado;
    // TIEMPO DEL SOPORTE
    private Integer tiempo;
    // CATEGORIA DEL SOPORTE
    private EnumCategoria categoria;
    // AGENTE QUIEN REALIZA EL SOPORTE
    private String agente;
    // ESTADO DE ELIMINADO
    private Boolean eliminado;
    // PENDIENTE DE LLAMAR
    private Boolean pendiente;
    // EL SOPORTE ES UNA INCIDENCIA
    private Boolean incidence;
    // FECHA DE CARGUE DE PENDIENTE
    private Long pending;
    // GUIA DE SERVIENTREGA
    private Long guia;
    // PENDIENTE DE LLAMAR
    private String evidencia;

}

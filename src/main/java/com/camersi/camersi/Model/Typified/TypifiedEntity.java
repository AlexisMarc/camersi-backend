package com.camersi.camersi.Model.Typified;


import com.camersi.camersi.Model.Pending.PendingEntity;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Utils.EnumCategoria;
import com.camersi.camersi.Utils.EnumCertificado;
import com.camersi.camersi.Utils.EnumSoporte;
import com.camersi.camersi.Utils.EnumSubSoporte;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "typified")
public class TypifiedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private Long id;
    // DOCUMENTO DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String identidad;
    // NOMBRE DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String nombre;
    // TELEFONO DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String telefono;
    // COMPAÑIA DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String company;
    // EMAIL DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String email;
    // TIPO DE CERTIFICADO EMITIDO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumCertificado certificado;
    // TIPO DE SOPORTE REALIZADO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumSoporte soporte;
    // SUB TIPO DE SOPORTE
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnumSubSoporte subSoporte;
    // FECHA DEL SOPORTE
    @Column(nullable = false)
    private String fecha;
    // HORA DEL SOPORTE
    @Column(nullable = false)
    private String hora;
    // OBSERVACIONES DEL SOPORTER
    @Column(nullable = false, columnDefinition = "TEXT")
    private String observacion;
    // ESTADO DEL SOPORTE
    @Column(nullable = true)
    private Boolean estado;
    // TIEMPO DEL SOPORTE
    @Column(nullable = false)
    private Integer tiempo;
    // CATEGORIA DEL SOPORTE
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnumCategoria categoria;
    // AGENTE QUIEN REALIZA EL SOPORTE
    @ManyToOne(optional = true)
    @JsonManagedReference(value = "usuario_typified")
    private UsuarioEntity agente;
    // ESTADO DE ELIMINADO
    @Column(nullable = true)
    private Boolean eliminado;

    // PENDIENTE DE LLAMAR
    @Column(nullable = true)
    private Boolean pendiente;

    // EL SOPORTE ES UNA INCIDENCIA
    @Column(nullable = true)
    private Boolean incidence;

    // FECHA DE CARGUE DE PENDIENTE
    @ManyToOne(optional = true)
    @JsonManagedReference(value = "typified_pending")
    private PendingEntity pending;

    // GUIA DE SERVIENTREGA
    @Column(nullable = true)
    private Long guia;

    // PENDIENTE DE LLAMAR
    @Column(nullable = true)
    private String evidencia;

    public TypifiedEntity(Long id, String identidad, String nombre, String telefono, String company, String email,
            EnumCertificado certificado, EnumSoporte soporte, EnumSubSoporte subSoporte, String fecha, String hora,
            String observacion, Boolean estado, Integer tiempo, EnumCategoria categoria, UsuarioEntity agente,
            Boolean eliminado, Boolean pendiente, Boolean incidence, PendingEntity pending, Long guia,
            String evidencia) {
        this.id = id;
        this.identidad = identidad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.company = company;
        this.email = email;
        this.certificado = certificado;
        this.soporte = soporte;
        this.subSoporte = subSoporte;
        this.fecha = fecha;
        this.hora = hora;
        this.observacion = observacion;
        this.estado = estado;
        this.tiempo = tiempo;
        this.categoria = categoria;
        this.agente = agente;
        this.eliminado = eliminado;
        this.pendiente = pendiente;
        this.incidence = incidence;
        this.pending = pending;
        this.guia = guia;
        this.evidencia = evidencia;
    }

    
}

package com.camersi.camersi.Model.Upload;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "upload")
@Setter
@Getter
@NoArgsConstructor
public class UploadEntity {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOMBRE DEL ARCHIVO
    @Column(nullable = false, unique = true)
    @NaturalId
    private String nombre;

    // FECHA DE CREACION
    @Column(nullable = false)
    private String fecha;

    public UploadEntity(Long id, String nombre, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    
}

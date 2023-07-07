package com.camersi.camersi.Model.Pending;

import java.util.List;

import com.camersi.camersi.Model.Typified.TypifiedEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pending")
@Setter
@Getter
@NoArgsConstructor
public class PendingEntity {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FECHA DE CARGADO
    @Column(nullable = false, unique = true)
    private String fecha;

    // FECHA DE CREACION
    @Column(nullable = false)
    private String creacion;

    // TIPIFICADOS REALIZADOS POR EL AGENTE
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pending")
    @JsonBackReference(value = "typified_pending")
    private List<TypifiedEntity> typified;

    public PendingEntity(Long id, String fecha, String creacion, List<TypifiedEntity> typified) {
        this.id = id;
        this.fecha = fecha;
        this.creacion = creacion;
        this.typified = typified;
    }
}

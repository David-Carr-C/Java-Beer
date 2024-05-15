package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.DATE;
import static jakarta.persistence.TemporalType.TIME;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "optical_fiber_report")
public class OpticalFiberReportEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // Varios reportes pueden ser creados por un solo usuario
    @ManyToOne(fetch = EAGER, targetEntity = UserEntity.class, cascade = PERSIST)
    @JoinColumn(name = "id_user")
    private String idUser;

    @ManyToOne(fetch = EAGER, targetEntity = ActivityEntity.class, cascade = PERSIST)
    @JoinColumn(name = "id_activity")
    private String idActivity;

    @ManyToOne(fetch = EAGER, targetEntity = MaterialEntity.class, cascade = PERSIST)
    @JoinColumn(name = "id_material")
    private String idMaterial;

    @ManyToOne(fetch = EAGER, targetEntity = UnitEntity.class, cascade = PERSIST)
    @JoinColumn(name = "id_unit")
    private String idUnit;

    // Un reporte tiene imagenes Lob (Large Object) y por rendimiento se carga de manera Lazy
    @Lob
    @Basic(fetch = LAZY)
    @Column(nullable = false)
    private byte[] image;

    @Temporal(DATE)
    private Date date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Temporal(TIME)
    private Time hour;

    private String description;
    private String quantity;
    private String address;

}

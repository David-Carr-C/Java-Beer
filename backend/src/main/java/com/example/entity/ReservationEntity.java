package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.DATE;
import static jakarta.persistence.TemporalType.TIME;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(DATE)
    @Column(nullable = false)
    private Date date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Temporal(TIME)
    @Column(nullable = false)
    private Time hour;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ComboEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_combo", nullable = false)
    private Long idCombo;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TableEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_table", nullable = false)
    private Long idTable;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ClientEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_client", nullable = false)
    private Long idClient;

    @Column(nullable = false)
    private Byte duration;

    private Byte partySize;
    private String status;
    private Byte isDeleted;
}

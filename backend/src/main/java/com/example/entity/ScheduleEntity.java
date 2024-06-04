package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.TIME;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "schedules")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Temporal(TIME)
    @Column(nullable = false)
    private Time hour;
}

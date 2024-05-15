package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpticalFiberReportDTO {
    private String idUser;

    @NotBlank
    private String idActivity;

    @NotBlank
    private String idMaterial;

    @NotBlank
    private String idUnit;

    @NotBlank
    @Size(min = 2)
    private byte[] image;

    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @NotBlank
    private Time hour;

    @NotBlank
    private String description;

    @NotBlank
    @Min(1)
    @Max(100000)
    private String quantity;

    @NotBlank
    private String address;
}

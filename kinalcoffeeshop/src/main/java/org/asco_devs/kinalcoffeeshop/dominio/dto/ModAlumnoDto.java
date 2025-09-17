package org.asco_devs.kinalcoffeeshop.dominio.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public record ModAlumnoDto(
    String name,
    String lastName,
    String carnet,
    String email,
    String genre,
    LocalDate birthDate,
    String password
){}

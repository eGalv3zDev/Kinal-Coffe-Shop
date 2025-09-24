package org.asco_devs.kinalcoffeeshop.dominio.dto.alumno;

import java.time.LocalDate;

public record ModAlumnoDto(
    String name,
    String lastName,
    String carnet,
    String email,
    String genre,
    LocalDate birthDate,
    String password
){}

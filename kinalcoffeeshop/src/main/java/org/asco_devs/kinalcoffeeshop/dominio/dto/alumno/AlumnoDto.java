package org.asco_devs.kinalcoffeeshop.dominio.dto.alumno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AlumnoDto (
        Long id,
        @NotBlank(message = "El nombre no puede estar vacio")
        String name,
        @NotBlank(message = "El apellido no puede estar vacio")
        String lastName,
        @NotBlank(message = "El carnet no puede estar vacio")
        String carnet,
        @NotBlank(message = "El correo no puede estar vacio")
        String email,
        @NotBlank(message = "El genero no puede estar vacio")
        String genre,
        @NotNull(message = "La fecha de nacimiento no puede estar vacia")
        LocalDate birthDate,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
){}
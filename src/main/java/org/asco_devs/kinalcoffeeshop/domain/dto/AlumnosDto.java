package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.asco_devs.kinalcoffeeshop.domain.model.Genero;

import java.time.LocalDate;

public record AlumnosDto(
        Long idAlumno,

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El carnet es obligatorio")
        String carnet,

        @Email(message = "Debe ser un formato de correo válido")
        String correo,

        @NotNull(message = "El género es obligatorio")
        Genero genero,

        @Past(message = "La fecha de nacimiento debe ser en el pasado")
        LocalDate fechaNacimiento,

        @NotBlank(message = "La contraseña es obligatoria")
        String contrasena
) {
}
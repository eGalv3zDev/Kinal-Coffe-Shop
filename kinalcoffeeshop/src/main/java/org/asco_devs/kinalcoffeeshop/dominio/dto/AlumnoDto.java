package org.asco_devs.kinalcoffeeshop.dominio.dto;


import jakarta.validation.constraints.NotBlank;

import java.util.Date;

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
        @NotBlank(message = "La fecha de nacimiento no puede estar vacia")
        Date dateBirth,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
){}

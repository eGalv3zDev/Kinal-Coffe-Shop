package org.asco_devs.kinalcoffeeshop.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public record UsuarioConCreditoDto(
        Long id,
        @NotBlank(message = "El nombre no puede estar vacio")
        String name,
        @NotBlank(message = "El apellido no puede estar vacio")
        String lastName,
        String phone,
        String email,
        @NotBlank(message = "El genero no puede estar vacio")
        String gender,
        Date dateBirth,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
) {}
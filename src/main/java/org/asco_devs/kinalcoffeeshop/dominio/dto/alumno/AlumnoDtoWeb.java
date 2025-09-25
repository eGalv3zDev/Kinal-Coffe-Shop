package org.asco_devs.kinalcoffeeshop.dominio.dto.alumno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDtoWeb {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;

    @NotBlank(message = "El carnet no puede estar vacio")
    private String carnet;

    @NotBlank(message = "El correo no puede estar vacio")
    private String email;

    @NotBlank(message = "El genero no puede estar vacio")
    private String genre;

    @NotNull(message = "La fecha de nacimiento no puede estar vacia")
    private LocalDate birthDate;

    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;
}


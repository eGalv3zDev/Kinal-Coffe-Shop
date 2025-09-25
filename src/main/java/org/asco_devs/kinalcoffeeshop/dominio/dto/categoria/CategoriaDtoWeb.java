package org.asco_devs.kinalcoffeeshop.dominio.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDtoWeb {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
}

package org.asco_devs.kinalcoffeeshop.dominio.dto.pedido;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.asco_devs.kinalcoffeeshop.persistence.entity.AlumnoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;

import java.time.LocalDateTime;

public record PedidoDto(
        Long id,
        @NotNull(message = "La fecha no puede estar vacia")
        LocalDateTime date,
        @NotNull(message = "El total no puede estar vacio")
        @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
        Double total,
        @NotBlank(message = "El estado no puede estar vacio")
        String state,
        AlumnoEntity studentId,
        UsuarioConCreditoEntity creditUserId
) {
}

        @NotNull(message = "El studentId no puede estar vacio")
        Long studentId,
        @NotNull(message = "El creditUserId no puede estar vacio")
        Long creditUserId
) {}

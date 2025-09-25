package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record CuentasDeCreditoDto(
        Long idCuenta,

        @NotNull(message = "El saldo es obligatorio")
        @PositiveOrZero(message = "El saldo no puede ser negativo")
        BigDecimal saldo,

        @NotNull(message = "El ID del usuario con crédito es obligatorio")
        Long idUsuarioCredito
) {
}
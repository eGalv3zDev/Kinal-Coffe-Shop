package org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito;

import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;

public record CuentaDeCreditoDto(
        Long id,
        Double credit,
        UsuarioConCreditoEntity creditUserId
) {
}

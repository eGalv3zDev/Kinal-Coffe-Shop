package org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito;

import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;

public record ModCuentaDeCreditoDto(
        Double credit,
        UsuarioConCreditoEntity creditUserId
) {
}

package org.asco_devs.kinalcoffeeshop.dominio.dto;

import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;

public record CuentaDeCreditoDto(
        Long id,
        Double credit,
        UsuarioConCreditoEntity userId
) {
}

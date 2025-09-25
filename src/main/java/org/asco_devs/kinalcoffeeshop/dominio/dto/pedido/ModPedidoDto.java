package org.asco_devs.kinalcoffeeshop.dominio.dto.pedido;

import org.asco_devs.kinalcoffeeshop.persistence.entity.AlumnoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;

public record ModPedidoDto(
        Double total,
        String state,
        AlumnoEntity studentId,
        UsuarioConCreditoEntity creditUserId
) {
}
package org.asco_devs.kinalcoffeeshop.dominio.dto.factura;

import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;

public record ModFacturaDto(
    Double total,
    PedidoEntity orderId
){}

package org.asco_devs.kinalcoffeeshop.dominio.dto.pedido;

import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoConDetalleDto(
        Long id,
        String alumnName,
        LocalDateTime date,
        BigDecimal total,
        String state,
        List<DetallePedidoDto> details
) {}

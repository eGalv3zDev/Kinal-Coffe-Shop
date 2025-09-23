package org.asco_devs.kinalcoffeeshop.dominio.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoConDetallesDto(
        Long idPedido,
        String alumnoNombre,
        LocalDateTime fecha,
        BigDecimal total,
        String estado,
        List<DetallePedidosDto> detalles
) {}

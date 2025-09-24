package org.asco_devs.kinalcoffeeshop.dominio.dto;

public record ModDetalleFacturaDto (
        Integer stock,
        Double subStock,
        Long facturaId,
        Long productId
) {
}
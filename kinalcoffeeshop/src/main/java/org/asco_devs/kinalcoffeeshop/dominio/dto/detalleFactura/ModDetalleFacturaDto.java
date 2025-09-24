package org.asco_devs.kinalcoffeeshop.dominio.dto.detalleFactura;

import org.asco_devs.kinalcoffeeshop.persistence.entity.FacturasEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;

public record ModDetalleFacturaDto (
    Integer stock,
    Double subStock,
    FacturasEntity facturaId,
    ProductoEntity productId
) {
    }

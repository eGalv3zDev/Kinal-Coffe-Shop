package org.asco_devs.kinalcoffeeshop.dominio.dto.detalleFactura;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.asco_devs.kinalcoffeeshop.persistence.entity.FacturasEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;

public record DetalleFacturaDto(
        Long id,
        @NotNull(message = "La cantidad no puede estar vacía")
        @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
        Integer stock,
        @NotNull(message = "El subtotal no puede estar vacío")
        @DecimalMin(value = "0.01", message = "El subtotal debe ser mayor a 0")
        Double subStock,
        @NotNull(message = "El ID de la factura no puede estar vacío")
        FacturasEntity facturaId,
        @NotNull(message = "El ID del producto no puede estar vacío")
        ProductoEntity productId
) {
}

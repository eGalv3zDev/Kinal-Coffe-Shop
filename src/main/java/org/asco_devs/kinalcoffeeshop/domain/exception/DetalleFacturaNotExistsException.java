package org.asco_devs.kinalcoffeeshop.domain.exception;

public class DetalleFacturaNotExistsException extends RuntimeException {
    public DetalleFacturaNotExistsException(Long idDetalleFactura) {
        super("No se encontró un detalle de factura con el ID: " + idDetalleFactura);
    }
}
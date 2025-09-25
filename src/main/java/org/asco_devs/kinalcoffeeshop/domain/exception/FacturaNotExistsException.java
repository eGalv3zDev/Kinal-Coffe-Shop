package org.asco_devs.kinalcoffeeshop.domain.exception;

public class FacturaNotExistsException extends RuntimeException {
    public FacturaNotExistsException(Long idFactura) {
        super("No se encontró una factura con el ID: " + idFactura);
    }
}
package org.asco_devs.kinalcoffeeshop.domain.exception;

public class FacturaExistsException extends RuntimeException {
    public FacturaExistsException(Long idPedido) {
        super("Ya existe una factura para el pedido con ID: " + idPedido);
    }
}
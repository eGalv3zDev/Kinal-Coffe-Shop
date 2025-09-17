package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class DetallePedidosExistsException extends RuntimeException {
    public DetallePedidosExistsException(Long idDetalle) {
        super("El detalle de pedido con ID: " + idDetalle + " ya existe.");
    }
}


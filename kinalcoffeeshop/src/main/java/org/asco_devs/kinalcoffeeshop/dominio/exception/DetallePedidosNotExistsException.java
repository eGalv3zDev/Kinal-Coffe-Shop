package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class DetallePedidosNotExistsException extends RuntimeException {
    public DetallePedidosNotExistsException(Long idDetalle) {
        super("El detalle de pedido con ID: " + idDetalle + " no existe en el sistema.");
    }
}
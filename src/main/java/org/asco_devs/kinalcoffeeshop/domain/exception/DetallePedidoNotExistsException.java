package org.asco_devs.kinalcoffeeshop.domain.exception;
public class DetallePedidoNotExistsException extends RuntimeException {
    public DetallePedidoNotExistsException(Long idDetalle) {
        super("No se encontró un detalle de pedido con el ID: " + idDetalle);
    }
}
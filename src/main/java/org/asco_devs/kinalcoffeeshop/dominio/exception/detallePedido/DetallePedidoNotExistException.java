package org.asco_devs.kinalcoffeeshop.dominio.exception.detallePedido;

public class DetallePedidoNotExistException extends RuntimeException {
    public DetallePedidoNotExistException(Long idDetalle) {
        super("El detalle de pedido con ID: " + idDetalle + " no existe en el sistema.");
    }
}
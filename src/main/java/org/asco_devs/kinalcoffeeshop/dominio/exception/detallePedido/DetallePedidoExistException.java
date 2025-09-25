package org.asco_devs.kinalcoffeeshop.dominio.exception.detallePedido;

public class DetallePedidoExistException extends RuntimeException {
    public DetallePedidoExistException(Long idDetalle) {
        super("El detalle de pedido con ID: " + idDetalle + " ya existe.");
    }
}


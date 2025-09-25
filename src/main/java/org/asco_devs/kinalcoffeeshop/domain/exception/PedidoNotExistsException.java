package org.asco_devs.kinalcoffeeshop.domain.exception;

public class PedidoNotExistsException extends RuntimeException {
    public PedidoNotExistsException(Long idPedido) {
        super("No se encontró un pedido con el ID: " + idPedido);
    }
}
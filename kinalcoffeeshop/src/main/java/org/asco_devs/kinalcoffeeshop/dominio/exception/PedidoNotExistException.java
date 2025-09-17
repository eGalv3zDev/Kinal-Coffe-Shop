package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class PedidoNotExistException extends RuntimeException {
    public PedidoNotExistException(Long idPedido) {
        super("El pedido con codigo " + idPedido + " no existe");
    }
}
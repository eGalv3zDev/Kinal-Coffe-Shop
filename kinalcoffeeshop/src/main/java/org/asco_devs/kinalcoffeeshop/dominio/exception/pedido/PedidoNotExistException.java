package org.asco_devs.kinalcoffeeshop.dominio.exception.pedido;

public class PedidoNotExistException extends RuntimeException {
    public PedidoNotExistException(Long idPedido) {
        super("El pedido con codigo " + idPedido + " no existe");
    }
}
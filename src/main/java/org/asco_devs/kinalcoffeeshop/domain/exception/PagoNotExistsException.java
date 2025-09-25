package org.asco_devs.kinalcoffeeshop.domain.exception;

public class PagoNotExistsException extends RuntimeException {
    public PagoNotExistsException(Long idPago) {
        super("No se encontró un pago con el ID: " + idPago);
    }
}
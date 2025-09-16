package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class PagoNotExistException extends RuntimeException {
    public PagoNotExistException(Long idPago) {
        super("El pago con codigo " + idPago + " no existe");
    }
}
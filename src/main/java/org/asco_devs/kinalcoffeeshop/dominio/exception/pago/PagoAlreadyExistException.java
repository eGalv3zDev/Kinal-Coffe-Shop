package org.asco_devs.kinalcoffeeshop.dominio.exception.pago;

public class PagoAlreadyExistException extends RuntimeException {
    public PagoAlreadyExistException(String message) {
        super("El pago con ID " + message + " ya existe");
    }
}
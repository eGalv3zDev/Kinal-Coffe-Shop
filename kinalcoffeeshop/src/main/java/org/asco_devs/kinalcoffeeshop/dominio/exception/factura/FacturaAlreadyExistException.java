package org.asco_devs.kinalcoffeeshop.dominio.exception.factura;

public class FacturaAlreadyExistException extends RuntimeException {
    public FacturaAlreadyExistException(String message) {
        super("La factura con ID"+message+" ya existe");
    }
}

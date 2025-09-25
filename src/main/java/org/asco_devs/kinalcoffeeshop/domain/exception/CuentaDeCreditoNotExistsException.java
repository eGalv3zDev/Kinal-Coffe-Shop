package org.asco_devs.kinalcoffeeshop.domain.exception;

public class CuentaDeCreditoNotExistsException extends RuntimeException {
    public CuentaDeCreditoNotExistsException(Long idCuenta) {
        super("No se encontró una cuenta de crédito con el ID: " + idCuenta);
    }
}
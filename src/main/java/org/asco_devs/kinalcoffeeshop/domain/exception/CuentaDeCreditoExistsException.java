package org.asco_devs.kinalcoffeeshop.domain.exception;

public class CuentaDeCreditoExistsException extends RuntimeException {
    public CuentaDeCreditoExistsException(Long idUsuarioCredito) {
        super("El usuario con ID " + idUsuarioCredito + " ya tiene una cuenta de crédito asociada.");
    }
}
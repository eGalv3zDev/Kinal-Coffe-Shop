package org.asco_devs.kinalcoffeeshop.domain.exception;

public class UsuarioConCreditoNotExistsException extends RuntimeException {
    public UsuarioConCreditoNotExistsException(Long idUsuarioCredito) {
        super("No se encontró un usuario con crédito con el ID: " + idUsuarioCredito);
    }
}
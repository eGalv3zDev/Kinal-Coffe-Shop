package org.asco_devs.kinalcoffeeshop.domain.exception;

public class UsuarioConCreditoExistsException extends RuntimeException {
    public UsuarioConCreditoExistsException(String correo) {
        super("Ya existe un usuario con crédito con el correo: " + correo);
    }
}
package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class UsuarioConCreditoAlreadyExistException extends RuntimeException {
    public UsuarioConCreditoAlreadyExistException(String message) {
        super("El usuario con credito con nombre " + message + " ya existe");
    }
}
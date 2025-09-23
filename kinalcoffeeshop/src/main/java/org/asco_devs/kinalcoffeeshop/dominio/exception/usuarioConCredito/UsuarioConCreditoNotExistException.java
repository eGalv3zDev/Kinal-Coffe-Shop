package org.asco_devs.kinalcoffeeshop.dominio.exception.usuarioConCredito;

public class UsuarioConCreditoNotExistException extends RuntimeException {
    public UsuarioConCreditoNotExistException(String message) {
        super("El usuario con credito con nombre: " + message + " no existe");
    }
}
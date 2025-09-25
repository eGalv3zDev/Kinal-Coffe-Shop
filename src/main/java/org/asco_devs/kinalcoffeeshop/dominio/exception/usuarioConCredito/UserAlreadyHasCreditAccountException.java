package org.asco_devs.kinalcoffeeshop.dominio.exception.usuarioConCredito;

public class UserAlreadyHasCreditAccountException extends RuntimeException {
    public UserAlreadyHasCreditAccountException(String message) {
        super(message);
    }
}

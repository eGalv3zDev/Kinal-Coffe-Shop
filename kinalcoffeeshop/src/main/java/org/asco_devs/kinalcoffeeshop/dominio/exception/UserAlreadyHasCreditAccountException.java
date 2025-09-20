package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class UserAlreadyHasCreditAccountException extends RuntimeException {
    public UserAlreadyHasCreditAccountException(String message) {
        super(message);
    }
}

package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class CreditAccountNotExistException extends RuntimeException {
    public CreditAccountNotExistException(Long idCuenta) {
        super("La cuenta de credito con codigo " + idCuenta + " no existe");
    }
}

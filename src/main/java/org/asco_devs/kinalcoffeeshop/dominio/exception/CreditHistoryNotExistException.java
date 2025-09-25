package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class CreditHistoryNotExistException extends RuntimeException {
    public CreditHistoryNotExistException(Long idHistorial) {
        super("El historial de crédito con ID: " + idHistorial + " no existe en el sistema.");
    }
}

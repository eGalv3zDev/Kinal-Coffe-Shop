package org.asco_devs.kinalcoffeeshop.dominio.exception.cuentaDeCredito;

public class CreditHistoryNotExistException extends RuntimeException {
    public CreditHistoryNotExistException(Long idHistorial) {
        super("El historial de credito con codigo " + idHistorial + " no existe");
    }
}

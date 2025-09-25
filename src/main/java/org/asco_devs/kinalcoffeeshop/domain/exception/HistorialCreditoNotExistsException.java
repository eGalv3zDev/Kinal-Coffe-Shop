package org.asco_devs.kinalcoffeeshop.domain.exception;

public class HistorialCreditoNotExistsException extends RuntimeException {
    public HistorialCreditoNotExistsException(Long idHistorial) {
        super("No se encontró un registro en el historial de crédito con el ID: " + idHistorial);
    }
}
package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class LineaDeCreditoNotExistException extends RuntimeException {
    public LineaDeCreditoNotExistException(Long idConsumo) {
        super("La linea de credito con codigo " + idConsumo + " no existe");
    }
}
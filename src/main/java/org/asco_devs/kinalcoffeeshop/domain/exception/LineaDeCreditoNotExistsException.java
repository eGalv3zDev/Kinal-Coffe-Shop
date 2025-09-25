package org.asco_devs.kinalcoffeeshop.domain.exception;

public class LineaDeCreditoNotExistsException extends RuntimeException {
    public LineaDeCreditoNotExistsException(Long idConsumo) {
        super("No se encontró una línea de crédito (consumo) con el ID: " + idConsumo);
    }
}
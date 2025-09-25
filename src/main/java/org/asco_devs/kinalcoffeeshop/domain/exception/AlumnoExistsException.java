package org.asco_devs.kinalcoffeeshop.domain.exception;

public class AlumnoExistsException extends RuntimeException {
    public AlumnoExistsException(String carnet) {
        super("Ya existe un alumno con el carnet: " + carnet);
    }
}
package org.asco_devs.kinalcoffeeshop.domain.exception;

public class AlumnoNotExistsException extends RuntimeException {
    public AlumnoNotExistsException(Long idAlumno) {
        super("No se encontró un alumno con el ID: " + idAlumno);
    }
}
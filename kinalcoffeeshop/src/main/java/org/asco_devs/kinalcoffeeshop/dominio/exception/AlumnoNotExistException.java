package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class AlumnoNotExistException extends RuntimeException {
    public AlumnoNotExistException(String message) {
        super("El alumno con nombre: " + message + " no existe");
    }
}

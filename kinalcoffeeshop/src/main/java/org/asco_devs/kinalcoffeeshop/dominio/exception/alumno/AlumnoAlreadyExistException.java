package org.asco_devs.kinalcoffeeshop.dominio.exception.alumno;

public class AlumnoAlreadyExistException extends RuntimeException {
    public AlumnoAlreadyExistException(String message) {
        super("El alumno con nombre " + message + " ya existe");
    }
}

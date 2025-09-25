package org.asco_devs.kinalcoffeeshop.dominio.exception.alumno;

public class AlumnoNotExistException extends RuntimeException {
    public AlumnoNotExistException(Long idAlumno) {
        super("El alumno con codigo " + idAlumno + " no existe");
    }
}

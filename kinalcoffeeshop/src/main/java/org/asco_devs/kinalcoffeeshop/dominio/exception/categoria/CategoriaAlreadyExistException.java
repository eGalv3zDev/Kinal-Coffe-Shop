package org.asco_devs.kinalcoffeeshop.dominio.exception.categoria;

public class CategoriaAlreadyExistException extends RuntimeException {
    public CategoriaAlreadyExistException(String message) {
        super("La categoria con nombre " + message + " ya existe");
    }
}

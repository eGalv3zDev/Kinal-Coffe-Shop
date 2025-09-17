package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class CategoriaNotExistException extends RuntimeException {
    public CategoriaNotExistException(Long idCategoria) {
        super("La categoria con codigo " + idCategoria + " no existe");
    }
}

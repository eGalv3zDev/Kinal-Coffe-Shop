package org.asco_devs.kinalcoffeeshop.dominio.exception.categoria;

public class CategoriaNotExistException extends RuntimeException {
    public CategoriaNotExistException(Long idCategoria) {
        super("La categoria con codigo " + idCategoria + " no existe");
    }
}

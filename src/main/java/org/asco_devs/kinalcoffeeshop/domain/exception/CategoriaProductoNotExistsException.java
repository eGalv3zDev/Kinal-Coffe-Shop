package org.asco_devs.kinalcoffeeshop.domain.exception;

public class CategoriaProductoNotExistsException extends RuntimeException {
    public CategoriaProductoNotExistsException(Long idCategoria) {
        super("No se encontró una categoría de producto con el ID: " + idCategoria);
    }
}
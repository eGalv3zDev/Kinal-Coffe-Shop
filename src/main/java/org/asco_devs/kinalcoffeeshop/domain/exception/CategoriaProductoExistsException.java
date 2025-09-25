package org.asco_devs.kinalcoffeeshop.domain.exception;

public class CategoriaProductoExistsException extends RuntimeException {
    public CategoriaProductoExistsException(String nombre) {
        super("Ya existe una categoría de producto con el nombre: " + nombre);
    }
}
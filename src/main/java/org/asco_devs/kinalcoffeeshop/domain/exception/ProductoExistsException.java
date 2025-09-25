package org.asco_devs.kinalcoffeeshop.domain.exception;

public class ProductoExistsException extends RuntimeException {
    public ProductoExistsException(String nombre) {
        super("Ya existe un producto con el nombre: " + nombre);
    }
}
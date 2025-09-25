package org.asco_devs.kinalcoffeeshop.domain.exception;

public class ProductoNotExistsException extends RuntimeException {
    public ProductoNotExistsException(Long idProducto) {
        super("No se encontró un producto con el ID: " + idProducto);
    }
}
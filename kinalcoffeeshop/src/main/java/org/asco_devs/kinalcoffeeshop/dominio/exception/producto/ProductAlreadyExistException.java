package org.asco_devs.kinalcoffeeshop.dominio.exception.producto;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super("El producto con nombre " + message + " ya existe");
    }
}

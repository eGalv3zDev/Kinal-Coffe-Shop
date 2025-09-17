package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super("El producto con nombre " + message + " ya existe");
    }
}

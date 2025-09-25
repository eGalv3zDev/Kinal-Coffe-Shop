package org.asco_devs.kinalcoffeeshop.dominio.exception.producto;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(Long idProducto) {
        super("El producto con codigo " + idProducto + " no existe");
    }
}

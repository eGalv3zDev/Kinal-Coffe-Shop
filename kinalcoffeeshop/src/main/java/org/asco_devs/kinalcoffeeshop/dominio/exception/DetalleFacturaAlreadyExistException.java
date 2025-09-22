package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class DetalleFacturaAlreadyExistException extends RuntimeException {
    public DetalleFacturaAlreadyExistException(Long idDetalleFactura) {
        super("El detalle de la factura No: "+ idDetalleFactura + "ya existe");
    }
}

package org.asco_devs.kinalcoffeeshop.dominio.exception.detalleFactura;

public class DetalleFacturaAlreadyExistException extends RuntimeException {
    public DetalleFacturaAlreadyExistException(Long idDetalleFactura) {
        super("El detalle de la factura No: "+ idDetalleFactura + "ya existe");
    }
}

package org.asco_devs.kinalcoffeeshop.dominio.exception;

public class DetalleFacturaNotExistException extends RuntimeException {
    public DetalleFacturaNotExistException(Long idDetalleFactura) {
        super("El detalle de la factura No: "+ idDetalleFactura + "no existe");
    }
}


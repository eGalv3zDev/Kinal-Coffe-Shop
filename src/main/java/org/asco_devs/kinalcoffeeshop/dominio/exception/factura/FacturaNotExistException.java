package org.asco_devs.kinalcoffeeshop.dominio.exception.factura;

public class FacturaNotExistException extends RuntimeException {
    public FacturaNotExistException(Long idFactura) {
        super("La factura con ID"+idFactura+" no existe");
    }
}

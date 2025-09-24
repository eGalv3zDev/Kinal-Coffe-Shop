package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.DetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetalleFacturaDto;

import java.util.List;

public interface DetalleFacturaRepository {
    List<DetalleFacturaDto> obtenerDetalleFactura();
    DetalleFacturaDto buscarPorId(Long idDetalleFactura);
    DetalleFacturaDto guardarDetalleFactura(DetalleFacturaDto dto);
    DetalleFacturaDto modificarDetalleFactura(Long idDetalleFactura, ModDetalleFacturaDto mod);
    void eliminarDetalleFactura(Long idDetalleFactura);
}
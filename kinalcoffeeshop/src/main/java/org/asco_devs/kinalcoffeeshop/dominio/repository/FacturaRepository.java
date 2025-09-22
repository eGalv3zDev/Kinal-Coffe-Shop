package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModProductoDto;

import java.util.List;

public interface FacturaRepository {
    List<FacturaDto> obtenerFacturas();
    FacturaDto buscarFacturaPorId(Long idFactura);
    FacturaDto guardarFactura(FacturaDto dto);
    FacturaDto modificarFactura(Long idFactura, ModFacturaDto mod);
    void eliminarFactura(Long idFactura);
}

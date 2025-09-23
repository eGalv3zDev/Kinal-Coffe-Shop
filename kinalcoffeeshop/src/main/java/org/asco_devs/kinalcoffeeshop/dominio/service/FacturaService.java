package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaDto> obtenerFacturas() {
        return this.facturaRepository.obtenerFacturas();
    }

    public FacturaDto buscarFacturaPorId(Long idFactura) {
        return this.facturaRepository.buscarFacturaPorId(idFactura);
    }

    public FacturaDto guardarFactura(FacturaDto dto) {
        return this.facturaRepository.guardarFactura(dto);
    }

    public FacturaDto modificarFactura(Long idFactura, ModFacturaDto mod) {
        return this.facturaRepository.modificarFactura(idFactura, mod);
    }

    public void eliminarFactura(Long idFactura) {
        this.facturaRepository.eliminarFactura(idFactura);
    }
}

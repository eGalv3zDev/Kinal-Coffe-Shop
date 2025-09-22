package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.DetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.DetalleFacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFacturaService {
    private final DetalleFacturaRepository detalleFacturaRepository;

    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public List<DetalleFacturaDto> obtenerDetalleFactura() {
        return this.detalleFacturaRepository.obtenerDetalleFactura();
    }

    public DetalleFacturaDto buscarPorId(Long idDetalleFactura) {
        return this.detalleFacturaRepository.buscarPorId(idDetalleFactura);
    }

    public DetalleFacturaDto guardarDetalleFactura(DetalleFacturaDto dto) {
        return this.detalleFacturaRepository.guardarDetalleFactura(dto);
    }

    public DetalleFacturaDto modificarDetalleFactura(Long idDetalleFactura, ModDetalleFacturaDto mod) {
        return this.detalleFacturaRepository.modificarDetalleFactura(idDetalleFactura, mod);
    }

    public void eliminarDetalleFactura(Long idDetalleFactura) {
        this.detalleFacturaRepository.eliminarDetalleFactura(idDetalleFactura);
    }
}

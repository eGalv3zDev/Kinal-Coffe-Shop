package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<PagoDto> obtenerPagos() {
        return this.pagoRepository.obtenerPagos();
    }

    public PagoDto buscarPorId(Long idPago) {
        return this.pagoRepository.buscarPorId(idPago);
    }

    public PagoDto guardarPago(PagoDto dto) {
        return this.pagoRepository.guardarPago(dto);
    }

    public PagoDto modificarPago(Long idPago, ModPagoDto mod) {
        return this.pagoRepository.modificarPago(idPago, mod);
    }

    public void eliminarPago(Long idPago) {
        this.pagoRepository.eliminarPago(idPago);
    }
}
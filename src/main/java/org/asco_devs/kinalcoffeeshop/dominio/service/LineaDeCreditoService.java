package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.ModLineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.LineaDeCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaDeCreditoService {
    private final LineaDeCreditoRepository repository;

    public LineaDeCreditoService(LineaDeCreditoRepository repository) {
        this.repository = repository;
    }

    public List<LineaDeCreditoDto> obtenerLineasDeCredito() {
        return this.repository.obtenerLineasDeCredito();
    }

    public LineaDeCreditoDto buscarPorId(Long idConsumo) {
        return this.repository.buscarPorId(idConsumo);
    }

    public LineaDeCreditoDto guardarLineaDeCredito(LineaDeCreditoDto dto) {
        return this.repository.guardarLineaDeCredito(dto);
    }

    public LineaDeCreditoDto modificarLineaDeCredito(Long idConsumo, ModLineaDeCreditoDto mod) {
        return this.repository.modificarLineaDeCredito(idConsumo, mod);
    }

    public void eliminarLineaDeCredito(Long idConsumo) {
        this.repository.eliminarLineaDeCredito(idConsumo);
    }
}
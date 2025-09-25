package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.ModLineaDeCreditoDto;

import java.util.List;

public interface LineaDeCreditoRepository {
    List<LineaDeCreditoDto> obtenerLineasDeCredito();
    LineaDeCreditoDto buscarPorId(Long idConsumo);
    LineaDeCreditoDto guardarLineaDeCredito(LineaDeCreditoDto dto);
    LineaDeCreditoDto modificarLineaDeCredito(Long idConsumo, ModLineaDeCreditoDto mod);
    void eliminarLineaDeCredito(Long idConsumo);
}
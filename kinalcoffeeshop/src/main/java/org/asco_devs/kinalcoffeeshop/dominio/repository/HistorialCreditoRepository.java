package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.HistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModHistorialCreditoDto;

import java.util.List;

public interface HistorialCreditoRepository {
    List<HistorialCreditoDto> obtenerHistorialesCredito();
    HistorialCreditoDto buscarPorId(Long idHistorialCredito);
    HistorialCreditoDto guardarHistorialCredito(HistorialCreditoDto dto);
    HistorialCreditoDto modificarHistorialCredito(Long idHistorialCredito, ModHistorialCreditoDto mod);
    void eliminarHistorialCredito(Long idHistorialCredito);
}

package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.LineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModLineasDeCreditoDto;
import java.util.List;
import java.util.Optional;

public interface LineasDeCreditoRepository {
    List<LineasDeCreditoDto> obtenerTodas();
    Optional<LineasDeCreditoDto> buscarPorId(Long idConsumo);
    List<LineasDeCreditoDto> buscarPorIdCuenta(Long idCuenta);
    LineasDeCreditoDto guardar(LineasDeCreditoDto lineasDeCreditoDto);
    Optional<LineasDeCreditoDto> modificar(Long idConsumo, ModLineasDeCreditoDto modLineasDeCreditoDto);
    void eliminar(Long idConsumo);
}
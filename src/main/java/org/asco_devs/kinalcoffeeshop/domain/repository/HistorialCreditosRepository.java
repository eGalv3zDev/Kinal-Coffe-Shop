package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.HistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModHistorialCreditosDto;
import java.util.List;
import java.util.Optional;

public interface HistorialCreditosRepository {
    List<HistorialCreditosDto> obtenerTodos();
    Optional<HistorialCreditosDto> buscarPorId(Long idHistorial);
    List<HistorialCreditosDto> buscarPorIdCuenta(Long idCuenta);
    HistorialCreditosDto guardar(HistorialCreditosDto historialCreditosDto);
    Optional<HistorialCreditosDto> modificar(Long idHistorial, ModHistorialCreditosDto modHistorialCreditosDto);
    void eliminar(Long idHistorial);
}
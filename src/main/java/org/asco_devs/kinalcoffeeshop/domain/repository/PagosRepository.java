package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModPagosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PagosDto;
import java.util.List;
import java.util.Optional;

public interface PagosRepository {
    List<PagosDto> obtenerTodos();
    Optional<PagosDto> buscarPorId(Long idPago);
    List<PagosDto> buscarPorIdPedido(Long idPedido);
    PagosDto guardar(PagosDto pagosDto);
    Optional<PagosDto> modificar(Long idPago, ModPagosDto modPagosDto);
    void eliminar(Long idPago);
}
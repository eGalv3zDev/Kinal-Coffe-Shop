package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModPedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PedidosDto;
import java.util.List;
import java.util.Optional;

public interface PedidosRepository {
    List<PedidosDto> obtenerTodos();
    Optional<PedidosDto> buscarPorId(Long idPedido);
    PedidosDto guardar(PedidosDto pedidosDto);
    Optional<PedidosDto> modificar(Long idPedido, ModPedidosDto modPedidosDto);
    void eliminar(Long idPedido);
}
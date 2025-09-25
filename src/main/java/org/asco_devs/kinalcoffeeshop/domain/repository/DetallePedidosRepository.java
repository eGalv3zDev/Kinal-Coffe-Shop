package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetallePedidosDto;
import java.util.List;
import java.util.Optional;

public interface DetallePedidosRepository {
    List<DetallePedidosDto> obtenerTodos();
    Optional<DetallePedidosDto> buscarPorId(Long idDetalle);
    List<DetallePedidosDto> buscarPorIdPedido(Long idPedido);
    DetallePedidosDto guardar(DetallePedidosDto detallePedidosDto);
    Optional<DetallePedidosDto> modificar(Long idDetalle, ModDetallePedidosDto modDetallePedidosDto);
    void eliminar(Long idDetalle);
}
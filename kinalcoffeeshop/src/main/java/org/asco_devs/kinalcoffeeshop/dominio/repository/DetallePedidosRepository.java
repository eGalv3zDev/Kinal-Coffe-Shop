package org.asco_devs.kinalcoffeeshop.dominio.repository;


import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.DetallePedidosDto;

import java.util.List;

public interface DetallePedidosRepository {

    List<DetallePedidosDto> obtenerTodo();

    DetallePedidosDto buscarPorId(Long idDetalle);

    DetallePedidosDto guardarDetalle(DetallePedidosDto detalle);

    DetallePedidosDto modificarDetalle(Long idDetalle, ModDetallePedidosDto modDetalle);

    void eliminarDetalle(Long idDetalle);
}

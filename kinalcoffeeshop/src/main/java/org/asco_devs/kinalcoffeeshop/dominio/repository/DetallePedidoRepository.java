package org.asco_devs.kinalcoffeeshop.dominio.repository;


import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;

import java.util.List;

public interface DetallePedidoRepository {

    List<DetallePedidoDto> obtenerTodo();

    DetallePedidoDto buscarPorId(Long idDetalle);

    DetallePedidoDto guardarDetalle(DetallePedidoDto detalle);

    DetallePedidoDto modificarDetalle(Long idDetalle, ModDetallePedidoDto modDetalle);

    void eliminarDetalle(Long idDetalle);
}

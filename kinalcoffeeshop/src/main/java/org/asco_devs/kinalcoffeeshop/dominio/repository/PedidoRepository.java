package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.PedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPedidoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;

import java.util.List;

public interface PedidoRepository {

    List<PedidoDto> obtenerPedidos();
    PedidoDto buscarPorId(Long idPedido);
    PedidoDto guardarPedido(PedidoDto dto);
    PedidoDto modificarPedido(Long idPedido, ModPedidoDto mod);
    void eliminarPedido(Long idPedido);
    List<PedidoEntity> findByAlumnoNombre(String nombre);
}
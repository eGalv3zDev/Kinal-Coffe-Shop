package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModPedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.PedidoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.PedidosRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidosService {

    private final PedidosRepository pedidosRepository;

    public PedidosService(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    public List<PedidosDto> obtenerTodo() {
        return pedidosRepository.obtenerTodos();
    }

    public PedidosDto buscarPorCodigo(Long idPedido) {
        return pedidosRepository.buscarPorId(idPedido)
                .orElseThrow(() -> new PedidoNotExistsException(idPedido));
    }

    public PedidosDto guardarPedido(PedidosDto pedidosDto) {
        return pedidosRepository.guardar(pedidosDto);
    }

    public PedidosDto modificarPedido(Long idPedido, ModPedidosDto modDto) {
        return pedidosRepository.modificar(idPedido, modDto)
                .orElseThrow(() -> new PedidoNotExistsException(idPedido));
    }

    public void eliminarPedido(Long idPedido) {
        pedidosRepository.eliminar(idPedido);
    }
}
package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.DetallePedidoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.DetallePedidosRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetallePedidosService {

    private final DetallePedidosRepository detallePedidosRepository;

    public DetallePedidosService(DetallePedidosRepository detallePedidosRepository) {
        this.detallePedidosRepository = detallePedidosRepository;
    }

    public List<DetallePedidosDto> obtenerTodo() {
        return detallePedidosRepository.obtenerTodos();
    }

    public DetallePedidosDto buscarPorCodigo(Long idDetalle) {
        return detallePedidosRepository.buscarPorId(idDetalle)
                .orElseThrow(() -> new DetallePedidoNotExistsException(idDetalle));
    }

    public List<DetallePedidosDto> buscarPorPedido(Long idPedido) {
        return detallePedidosRepository.buscarPorIdPedido(idPedido);
    }

    public DetallePedidosDto guardarDetalle(DetallePedidosDto detallePedidosDto) {
        return detallePedidosRepository.guardar(detallePedidosDto);
    }

    public DetallePedidosDto modificarDetalle(Long idDetalle, ModDetallePedidosDto modDto) {
        return detallePedidosRepository.modificar(idDetalle, modDto)
                .orElseThrow(() -> new DetallePedidoNotExistsException(idDetalle));
    }

    public void eliminarDetalle(Long idDetalle) {
        detallePedidosRepository.eliminar(idDetalle);
    }
}
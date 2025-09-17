package org.asco_devs.kinalcoffeeshop.dominio.service;

import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.repository.DetallePedidosRepository;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.DetallePedidosDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DetallePedidosService {

    private final DetallePedidosRepository detallePedidosRepository;

    public DetallePedidosService(DetallePedidosRepository detallePedidosRepository) {
        this.detallePedidosRepository = detallePedidosRepository;
    }

    public List<DetallePedidosDto> obtenerTodo() {
        return this.detallePedidosRepository.obtenerTodo();
    }

    public DetallePedidosDto buscarPorId(Long idDetalle) {
        return this.detallePedidosRepository.buscarPorId(idDetalle);
    }

    public DetallePedidosDto guardarDetalle(DetallePedidosDto detalle) {
        return this.detallePedidosRepository.guardarDetalle(detalle);
    }

    public DetallePedidosDto modificarDetalle(Long idDetalle, ModDetallePedidosDto modDetalle) {
        return this.detallePedidosRepository.modificarDetalle(idDetalle, modDetalle);
    }

    public void eliminarDetalle(Long idDetalle) {
        this.detallePedidosRepository.eliminarDetalle(idDetalle);
    }
}

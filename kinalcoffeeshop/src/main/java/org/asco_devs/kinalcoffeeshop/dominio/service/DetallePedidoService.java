package org.asco_devs.kinalcoffeeshop.dominio.service;

import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.repository.DetallePedidoRepository;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<DetallePedidoDto> obtenerTodo() {
        return this.detallePedidoRepository.obtenerTodo();
    }

    public DetallePedidoDto buscarPorId(Long idDetalle) {
        return this.detallePedidoRepository.buscarPorId(idDetalle);
    }

    public DetallePedidoDto guardarDetalle(DetallePedidoDto detalle) {
        return this.detallePedidoRepository.guardarDetalle(detalle);
    }

    public DetallePedidoDto modificarDetalle(Long idDetalle, ModDetallePedidoDto modDetalle) {
        return this.detallePedidoRepository.modificarDetalle(idDetalle, modDetalle);
    }

    public void eliminarDetalle(Long idDetalle) {
        this.detallePedidoRepository.eliminarDetalle(idDetalle);
    }
}

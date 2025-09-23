package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.pedido.PedidoConDetalleDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pedido.PedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pedido.ModPedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.PedidoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PedidoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoService(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    public List<PedidoDto> obtenerPedidos() {
        return this.pedidoRepository.obtenerPedidos();
    }

    public PedidoDto buscarPorId(Long idPedido) {
        return this.pedidoRepository.buscarPorId(idPedido);
    }

    public PedidoDto guardarPedido(PedidoDto dto) {
        return this.pedidoRepository.guardarPedido(dto);
    }

    public PedidoDto modificarPedido(Long idPedido, ModPedidoDto mod) {
        return this.pedidoRepository.modificarPedido(idPedido, mod);
    }

    public void eliminarPedido(Long idPedido) {
        this.pedidoRepository.eliminarPedido(idPedido);
    }

    public List<PedidoConDetalleDto> obtenerPedidosPorAlumno(String nombre) {
        List<PedidoEntity> pedidos = pedidoRepository.findByAlumnoNombre(nombre);
        return pedidoMapper.toConDetallesDto(pedidos);
    }
}
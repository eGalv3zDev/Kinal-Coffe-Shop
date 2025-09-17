package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.PedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
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
}
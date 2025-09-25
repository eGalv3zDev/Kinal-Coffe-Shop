package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModPagosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PagosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.PagoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.PagosRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagosService {

    private final PagosRepository pagosRepository;

    public PagosService(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    public List<PagosDto> obtenerTodo() {
        return pagosRepository.obtenerTodos();
    }

    public PagosDto buscarPorCodigo(Long idPago) {
        return pagosRepository.buscarPorId(idPago)
                .orElseThrow(() -> new PagoNotExistsException(idPago));
    }

    public List<PagosDto> buscarPorPedido(Long idPedido) {
        return pagosRepository.buscarPorIdPedido(idPedido);
    }

    public PagosDto guardarPago(PagosDto pagosDto) {
        return pagosRepository.guardar(pagosDto);
    }

    public PagosDto modificarPago(Long idPago, ModPagosDto modDto) {
        return pagosRepository.modificar(idPago, modDto)
                .orElseThrow(() -> new PagoNotExistsException(idPago));
    }

    public void eliminarPago(Long idPago) {
        pagosRepository.eliminar(idPago);
    }
}
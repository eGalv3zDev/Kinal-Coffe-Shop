package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.HistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModHistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.HistorialCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.HistorialCreditosRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistorialCreditosService {

    private final HistorialCreditosRepository historialCreditosRepository;

    public HistorialCreditosService(HistorialCreditosRepository historialCreditosRepository) {
        this.historialCreditosRepository = historialCreditosRepository;
    }

    public List<HistorialCreditosDto> obtenerTodo() {
        return historialCreditosRepository.obtenerTodos();
    }

    public HistorialCreditosDto buscarPorCodigo(Long idHistorial) {
        return historialCreditosRepository.buscarPorId(idHistorial)
                .orElseThrow(() -> new HistorialCreditoNotExistsException(idHistorial));
    }

    public List<HistorialCreditosDto> buscarPorCuenta(Long idCuenta) {
        return historialCreditosRepository.buscarPorIdCuenta(idCuenta);
    }

    public HistorialCreditosDto guardarRegistro(HistorialCreditosDto historialCreditosDto) {
        return historialCreditosRepository.guardar(historialCreditosDto);
    }

    public HistorialCreditosDto modificarRegistro(Long idHistorial, ModHistorialCreditosDto modDto) {
        return historialCreditosRepository.modificar(idHistorial, modDto)
                .orElseThrow(() -> new HistorialCreditoNotExistsException(idHistorial));
    }

    public void eliminarRegistro(Long idHistorial) {
        historialCreditosRepository.eliminar(idHistorial);
    }
}
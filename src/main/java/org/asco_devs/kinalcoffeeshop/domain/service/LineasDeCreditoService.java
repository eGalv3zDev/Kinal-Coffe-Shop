package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.LineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModLineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.LineaDeCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.LineasDeCreditoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LineasDeCreditoService {

    private final LineasDeCreditoRepository lineasDeCreditoRepository;

    public LineasDeCreditoService(LineasDeCreditoRepository lineasDeCreditoRepository) {
        this.lineasDeCreditoRepository = lineasDeCreditoRepository;
    }

    public List<LineasDeCreditoDto> obtenerTodo() {
        return lineasDeCreditoRepository.obtenerTodas();
    }

    public LineasDeCreditoDto buscarPorCodigo(Long idConsumo) {
        return lineasDeCreditoRepository.buscarPorId(idConsumo)
                .orElseThrow(() -> new LineaDeCreditoNotExistsException(idConsumo));
    }

    public List<LineasDeCreditoDto> buscarPorCuenta(Long idCuenta) {
        return lineasDeCreditoRepository.buscarPorIdCuenta(idCuenta);
    }

    public LineasDeCreditoDto guardarConsumo(LineasDeCreditoDto lineasDeCreditoDto) {
        return lineasDeCreditoRepository.guardar(lineasDeCreditoDto);
    }

    public LineasDeCreditoDto modificarConsumo(Long idConsumo, ModLineasDeCreditoDto modDto) {
        return lineasDeCreditoRepository.modificar(idConsumo, modDto)
                .orElseThrow(() -> new LineaDeCreditoNotExistsException(idConsumo));
    }

    public void eliminarConsumo(Long idConsumo) {
        lineasDeCreditoRepository.eliminar(idConsumo);
    }
}
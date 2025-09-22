package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.HistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModHistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.HistorialCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialCreditoService {
    private final HistorialCreditoRepository historialCreditoRepository;
    public HistorialCreditoService(HistorialCreditoRepository historialCreditoRepository) {
        this.historialCreditoRepository = historialCreditoRepository;
    }

    public List<HistorialCreditoDto> obtenerHistoriales(){
        return this.historialCreditoRepository.obtenerHistorialesCredito();
    }

    public HistorialCreditoDto buscarPorId(Long idHistorial){
        return this.historialCreditoRepository.buscarPorId(idHistorial);
    }

    public HistorialCreditoDto guardarHistorial(HistorialCreditoDto dto){
        return this.historialCreditoRepository.guardarHistorialCredito(dto);
    }

    public HistorialCreditoDto modificarHistorial(Long idHistorial, ModHistorialCreditoDto mod){
        return this.historialCreditoRepository.modificarHistorialCredito(idHistorial, mod);
    }

    public void eliminarHistorial(Long idHistorial){
        this.historialCreditoRepository.eliminarHistorialCredito(idHistorial);
    }
}

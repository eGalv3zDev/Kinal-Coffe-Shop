package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.DetalleFacturaNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.DetalleFacturasRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleFacturasService {

    private final DetalleFacturasRepository detalleFacturasRepository;

    public DetalleFacturasService(DetalleFacturasRepository detalleFacturasRepository) {
        this.detalleFacturasRepository = detalleFacturasRepository;
    }

    public List<DetalleFacturasDto> obtenerTodo() {
        return detalleFacturasRepository.obtenerTodos();
    }

    public DetalleFacturasDto buscarPorCodigo(Long idDetalleFactura) {
        return detalleFacturasRepository.buscarPorId(idDetalleFactura)
                .orElseThrow(() -> new DetalleFacturaNotExistsException(idDetalleFactura));
    }

    public List<DetalleFacturasDto> buscarPorFactura(Long idFactura) {
        return detalleFacturasRepository.buscarPorIdFactura(idFactura);
    }

    public DetalleFacturasDto guardarDetalle(DetalleFacturasDto detalleFacturasDto) {
        return detalleFacturasRepository.guardar(detalleFacturasDto);
    }

    public DetalleFacturasDto modificarDetalle(Long idDetalleFactura, ModDetalleFacturasDto modDto) {
        return detalleFacturasRepository.modificar(idDetalleFactura, modDto)
                .orElseThrow(() -> new DetalleFacturaNotExistsException(idDetalleFactura));
    }

    public void eliminarDetalle(Long idDetalleFactura) {
        detalleFacturasRepository.eliminar(idDetalleFactura);
    }
}
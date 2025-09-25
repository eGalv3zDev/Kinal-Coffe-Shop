package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.FacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.FacturaNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.FacturasRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FacturasService {

    private final FacturasRepository facturasRepository;

    public FacturasService(FacturasRepository facturasRepository) {
        this.facturasRepository = facturasRepository;
    }

    public List<FacturasDto> obtenerTodo() {
        return facturasRepository.obtenerTodas();
    }

    public FacturasDto buscarPorCodigo(Long idFactura) {
        return facturasRepository.buscarPorId(idFactura)
                .orElseThrow(() -> new FacturaNotExistsException(idFactura));
    }

    public Optional<FacturasDto> buscarPorPedido(Long idPedido) {
        return facturasRepository.buscarPorIdPedido(idPedido);
    }

    public FacturasDto guardarFactura(FacturasDto facturasDto) {
        return facturasRepository.guardar(facturasDto);
    }

    public FacturasDto modificarFactura(Long idFactura, ModFacturasDto modDto) {
        return facturasRepository.modificar(idFactura, modDto)
                .orElseThrow(() -> new FacturaNotExistsException(idFactura));
    }

    public void eliminarFactura(Long idFactura) {
        facturasRepository.eliminar(idFactura);
    }
}
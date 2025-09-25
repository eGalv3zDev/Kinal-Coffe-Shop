package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.FacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModFacturasDto;
import java.util.List;
import java.util.Optional;

public interface FacturasRepository {
    List<FacturasDto> obtenerTodas();
    Optional<FacturasDto> buscarPorId(Long idFactura);
    Optional<FacturasDto> buscarPorIdPedido(Long idPedido);
    FacturasDto guardar(FacturasDto facturasDto);
    Optional<FacturasDto> modificar(Long idFactura, ModFacturasDto modFacturasDto);
    void eliminar(Long idFactura);
}
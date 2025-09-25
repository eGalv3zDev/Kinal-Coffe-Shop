package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetalleFacturasDto;
import java.util.List;
import java.util.Optional;

public interface DetalleFacturasRepository {
    List<DetalleFacturasDto> obtenerTodos();
    Optional<DetalleFacturasDto> buscarPorId(Long idDetalleFactura);
    List<DetalleFacturasDto> buscarPorIdFactura(Long idFactura);
    DetalleFacturasDto guardar(DetalleFacturasDto detalleFacturasDto);
    Optional<DetalleFacturasDto> modificar(Long idDetalleFactura, ModDetalleFacturasDto modDetalleFacturasDto);
    void eliminar(Long idDetalleFactura);
}
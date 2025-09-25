package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ProductosDto;

import java.util.List;
import java.util.Optional;

public interface ProductosRepository {
    List<ProductosDto> obtenerTodos();
    Optional<ProductosDto> buscarPorId(Long idProducto);
    ProductosDto guardar(ProductosDto productosDto);
    Optional<ProductosDto> modificar(Long idProducto, ModProductosDto modProductosDto);
    void eliminar(Long idProducto);
}
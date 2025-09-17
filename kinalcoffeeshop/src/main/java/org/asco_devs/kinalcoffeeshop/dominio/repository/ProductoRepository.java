package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.ModProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ProductoDto;

import java.util.List;

public interface ProductoRepository {
    List<ProductoDto> obtenerProductos();
    ProductoDto buscarPorId(Long idProducto);;
    ProductoDto guardarProducto(ProductoDto dto);
    ProductoDto modificarProducto(Long idProducto, ModProductoDto mod);
    void eliminarProducto(Long idProducto);;
}

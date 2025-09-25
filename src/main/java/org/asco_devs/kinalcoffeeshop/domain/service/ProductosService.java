package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.ProductoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService {
    private final ProductosRepository productosRepository;

    public ProductosService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    public List<ProductosDto> obtenerTodo() {
        return productosRepository.obtenerTodos();
    }

    public ProductosDto buscarPorCodigo(Long idProducto) {
        return productosRepository.buscarPorId(idProducto)
                .orElseThrow(() -> new ProductoNotExistsException(idProducto));
    }

    public ProductosDto guardarProducto(ProductosDto productosDto) {
        return productosRepository.guardar(productosDto);
    }

    public ProductosDto modificarProducto(Long idProducto, ModProductosDto modDto) {
        return productosRepository.modificar(idProducto, modDto)
                .orElseThrow(() -> new ProductoNotExistsException(idProducto));
    }

    public void eliminarProducto(Long idProducto) {
        productosRepository.eliminar(idProducto);
    }
}
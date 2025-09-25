package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.producto.ModProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.producto.ProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDto> obtenerProductos(){
        return this.productoRepository.obtenerProductos();
    }

    public ProductoDto buscarPorId(Long idProducto){
        return this.productoRepository.buscarPorId(idProducto);
    }

    public ProductoDto guardarProducto(ProductoDto dto){
        return this.productoRepository.guardarProducto(dto);
    }

    public ProductoDto modificarProducto(Long idProducto, ModProductoDto mod){
        return this.productoRepository.modificarProducto(idProducto, mod);
    }

    public void eliminarProducto(Long idProducto){
        this.productoRepository.eliminarProducto(idProducto);
    }
}

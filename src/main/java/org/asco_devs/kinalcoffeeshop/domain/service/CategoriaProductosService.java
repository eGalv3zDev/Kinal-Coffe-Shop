package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.CategoriaProductoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.CategoriaProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductosService {

    private final CategoriaProductosRepository categoriaProductosRepository;

    public CategoriaProductosService(CategoriaProductosRepository categoriaProductosRepository) {
        this.categoriaProductosRepository = categoriaProductosRepository;
    }

    public List<CategoriaProductosDto> obtenerTodo() {
        return categoriaProductosRepository.obtenerTodas();
    }

    public CategoriaProductosDto buscarPorCodigo(Long idCategoria) {
        return categoriaProductosRepository.buscarPorId(idCategoria)
                .orElseThrow(() -> new CategoriaProductoNotExistsException(idCategoria));
    }

    public CategoriaProductosDto guardarCategoria(CategoriaProductosDto categoriaProductosDto) {
        return categoriaProductosRepository.guardar(categoriaProductosDto);
    }

    public CategoriaProductosDto modificarCategoria(Long idCategoria, ModCategoriaProductosDto modDto) {
        return categoriaProductosRepository.modificar(idCategoria, modDto)
                .orElseThrow(() -> new CategoriaProductoNotExistsException(idCategoria));
    }

    public void eliminarCategoria(Long idCategoria) {
        categoriaProductosRepository.eliminar(idCategoria);
    }
}
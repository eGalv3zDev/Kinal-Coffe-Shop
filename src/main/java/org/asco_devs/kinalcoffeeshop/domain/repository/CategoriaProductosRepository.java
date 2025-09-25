package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCategoriaProductosDto;

import java.util.List;
import java.util.Optional;

public interface CategoriaProductosRepository {
    List<CategoriaProductosDto> obtenerTodas();
    Optional<CategoriaProductosDto> buscarPorId(Long idCategoria);
    CategoriaProductosDto guardar(CategoriaProductosDto categoriaProductosDto);
    Optional<CategoriaProductosDto> modificar(Long idCategoria, ModCategoriaProductosDto modCategoriaProductosDto);
    void eliminar(Long idCategoria);
}
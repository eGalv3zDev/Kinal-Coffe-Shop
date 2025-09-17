package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModCategoriaDto;

import java.util.List;

public interface CategoriaRepository {

    List<CategoriaDto> obtenerCategorias();
    CategoriaDto buscarPorId(Long idCategoria);
    CategoriaDto guardarCategoria(CategoriaDto dto);
    CategoriaDto modificarCategoria(Long idCategoria, ModCategoriaDto mod);
    void eliminarCategoria(Long idCategoria);
}

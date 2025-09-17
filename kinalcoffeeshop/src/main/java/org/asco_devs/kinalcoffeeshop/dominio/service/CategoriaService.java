package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDto> obtenerCategorias() {
        return this.categoriaRepository.obtenerCategorias();
    }

    public CategoriaDto buscarPorId(Long idCategoria) {
        return this.categoriaRepository.buscarPorId(idCategoria);
    }

    public CategoriaDto guardarCategoria(CategoriaDto dto){
        return this.categoriaRepository.guardarCategoria(dto);
    }

    public CategoriaDto modificarCategoria(Long idCategoria, ModCategoriaDto mod){
        return this.categoriaRepository.modificarCategoria(idCategoria, mod);
    }

    public void eliminarCategoria(Long idCategoria){
        this.categoriaRepository.eliminarCategoria(idCategoria);
    }
}

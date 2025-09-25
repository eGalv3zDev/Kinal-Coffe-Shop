package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.CategoriaProductoExistsException;
import org.asco_devs.kinalcoffeeshop.domain.exception.CategoriaProductoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.CategoriaProductosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudCategoriaProductosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaProductos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CategoriaProductosMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaProductosEntityRepository implements CategoriaProductosRepository {

    private final CrudCategoriaProductosEntity crudCategoriaProductosEntity;
    private final CategoriaProductosMapper categoriaProductosMapper;

    public CategoriaProductosEntityRepository(CrudCategoriaProductosEntity crudCategoriaProductosEntity, CategoriaProductosMapper categoriaProductosMapper) {
        this.crudCategoriaProductosEntity = crudCategoriaProductosEntity;
        this.categoriaProductosMapper = categoriaProductosMapper;
    }

    @Override
    public List<CategoriaProductosDto> obtenerTodas() {
        return categoriaProductosMapper.toDto((List<CategoriaProductos>) crudCategoriaProductosEntity.findAll());
    }

    @Override
    public Optional<CategoriaProductosDto> buscarPorId(Long idCategoria) {
        return crudCategoriaProductosEntity.findById(idCategoria)
                .map(categoriaProductosMapper::toDto);
    }

    @Override
    public CategoriaProductosDto guardar(CategoriaProductosDto categoriaProductosDto) {
        crudCategoriaProductosEntity.findByNombre(categoriaProductosDto.nombre())
                .ifPresent(c -> {
                    throw new CategoriaProductoExistsException(c.getNombre());
                });
        CategoriaProductos categoria = categoriaProductosMapper.toEntity(categoriaProductosDto);
        return categoriaProductosMapper.toDto(crudCategoriaProductosEntity.save(categoria));
    }

    @Override
    public Optional<CategoriaProductosDto> modificar(Long idCategoria, ModCategoriaProductosDto modDto) {
        return crudCategoriaProductosEntity.findById(idCategoria)
                .map(categoria -> {
                    categoria.setNombre(modDto.nombre());
                    return categoriaProductosMapper.toDto(crudCategoriaProductosEntity.save(categoria));
                });
    }

    @Override
    public void eliminar(Long idCategoria) {
        if (!crudCategoriaProductosEntity.existsById(idCategoria)) {
            throw new CategoriaProductoNotExistsException(idCategoria);
        }
        crudCategoriaProductosEntity.deleteById(idCategoria);
    }
}
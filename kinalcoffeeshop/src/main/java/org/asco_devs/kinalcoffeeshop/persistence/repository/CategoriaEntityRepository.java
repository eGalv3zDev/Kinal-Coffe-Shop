package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.categoria.CategoriaAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.categoria.CategoriaNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.CategoriaRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudCategoriaEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CategoriaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaEntityRepository implements CategoriaRepository {

    private final CrudCategoriaEntity crudCategoriaEntity;
    private final CategoriaMapper categoriaMapper;

    public CategoriaEntityRepository(CrudCategoriaEntity crudCategoriaEntity, CategoriaMapper categoriaMapper) {
        this.crudCategoriaEntity = crudCategoriaEntity;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaDto> obtenerCategorias() {
        return this.categoriaMapper.toDto(this.crudCategoriaEntity.findAll());
    }

    @Override
    public CategoriaDto buscarPorId(Long idCategoria) {
        return this.categoriaMapper.toDto(this.crudCategoriaEntity.findById(idCategoria).orElse(null));
    }

    @Override
    public CategoriaDto guardarCategoria(CategoriaDto dto) {
        if(this.crudCategoriaEntity.findFirstByNombre(dto.name()) != null){
            throw new CategoriaAlreadyExistException(dto.name());
        }
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity = this.categoriaMapper.toEntity(dto);
        this.crudCategoriaEntity.save(categoriaEntity);

        return this.categoriaMapper.toDto(categoriaEntity);
    }

    @Override
    public CategoriaDto modificarCategoria(Long idCategoria, ModCategoriaDto mod) {
        CategoriaEntity categoriaEntity = this.crudCategoriaEntity.findById(idCategoria).orElse(null);
        if(categoriaEntity == null) {
            throw new CategoriaNotExistException(idCategoria);
        }
        this.categoriaMapper.modificarEntityFromDto(mod, categoriaEntity);
        return categoriaMapper.toDto(this.crudCategoriaEntity.save(categoriaEntity));
    }

    @Override
    public void eliminarCategoria(Long idCategoria) {
        CategoriaEntity categoriaEntity = this.crudCategoriaEntity.findById(idCategoria).orElse(null);
        if(categoriaEntity == null) {
            throw new CategoriaNotExistException(idCategoria);
        } else {
            this.crudCategoriaEntity.deleteById(idCategoria);
        }
    }
}

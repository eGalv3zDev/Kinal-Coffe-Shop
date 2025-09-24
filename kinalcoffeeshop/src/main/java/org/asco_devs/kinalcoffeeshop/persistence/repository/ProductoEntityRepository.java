package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.producto.ModProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.producto.ProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.producto.ProductAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.producto.ProductNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.ProductoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudProductoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.ProductoMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductoEntityRepository implements ProductoRepository {

    private final CrudProductoEntity crudProductoEntity;
    private final ProductoMapper productoMapper;

    public ProductoEntityRepository(CrudProductoEntity crudProductoEntity, ProductoMapper productoMapper) {
        this.crudProductoEntity = crudProductoEntity;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoDto> obtenerProductos() {
        return this.productoMapper.toDto(this.crudProductoEntity.findAll());
    }

    @Override
    public ProductoDto buscarPorId(Long idProducto) {
        return this.productoMapper.toDto(this.crudProductoEntity.findById(idProducto).orElse(null));
    }

    @Override
    public ProductoDto guardarProducto(ProductoDto dto) {
        if(this.crudProductoEntity.findFirstByNombre(dto.name()) != null){
            throw new ProductAlreadyExistException(dto.name());
        }
        ProductoEntity productoEntity = this.productoMapper.toEntity(dto);
        productoEntity.setFechaDeIngreso(LocalDate.now());
        this.crudProductoEntity.save(productoEntity);
        return this.productoMapper.toDto(productoEntity);
    }

    @Override
    public ProductoDto modificarProducto(Long idProducto, ModProductoDto mod) {
        ProductoEntity productoEntity = this.crudProductoEntity.findById(idProducto).orElse(null);
        if(productoEntity == null) {
            throw new ProductNotExistException(idProducto);
        }
        this.productoMapper.modificarEntityFromDto(mod, productoEntity);
        return this.productoMapper.toDto(this.crudProductoEntity.save(productoEntity));
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        ProductoEntity productoEntity = this.crudProductoEntity.findById(idProducto).orElse(null);
        if(productoEntity == null){
            throw new ProductNotExistException(idProducto);
        }
        else {
            this.crudProductoEntity.deleteById(idProducto);
        }
    }
}

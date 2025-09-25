package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.ProductoExistsException;
import org.asco_devs.kinalcoffeeshop.domain.exception.ProductoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.ProductosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudProductosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.ProductosMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductosEntityRepository implements ProductosRepository {

    private final CrudProductosEntity crudProductosEntity;
    private final ProductosMapper productosMapper;

    public ProductosEntityRepository(CrudProductosEntity crudProductosEntity, ProductosMapper productosMapper) {
        this.crudProductosEntity = crudProductosEntity;
        this.productosMapper = productosMapper;
    }

    @Override
    public List<ProductosDto> obtenerTodos() {
        return productosMapper.toDto((List<Productos>) crudProductosEntity.findAll());
    }

    @Override
    public Optional<ProductosDto> buscarPorId(Long idProducto) {
        return crudProductosEntity.findById(idProducto)
                .map(productosMapper::toDto);
    }

    @Override
    public ProductosDto guardar(ProductosDto productosDto) {
        crudProductosEntity.findByNombre(productosDto.nombre())
                .ifPresent(p -> {
                    throw new ProductoExistsException(p.getNombre());
                });
        Productos producto = productosMapper.toEntity(productosDto);
        return productosMapper.toDto(crudProductosEntity.save(producto));
    }

    @Override
    public Optional<ProductosDto> modificar(Long idProducto, ModProductosDto modDto) {
        return crudProductosEntity.findById(idProducto)
                .map(producto -> {
                    producto.setNombre(modDto.nombre());
                    producto.setDescripcion(modDto.descripcion());
                    producto.setPrecio(modDto.precio());
                    producto.setStock(modDto.stock());
                    producto.setFechaDeExpiracion(modDto.fechaDeExpiracion());
                    producto.setIdCategoria(modDto.idCategoria());
                    return productosMapper.toDto(crudProductosEntity.save(producto));
                });
    }

    @Override
    public void eliminar(Long idProducto) {
        if (!crudProductosEntity.existsById(idProducto)) {
            throw new ProductoNotExistsException(idProducto);
        }
        crudProductosEntity.deleteById(idProducto);
    }
}
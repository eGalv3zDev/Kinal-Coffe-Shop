package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.DetalleFacturaNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.DetalleFacturasRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudDetalleFacturasEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturas;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetalleFacturasMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DetalleFacturasEntityRepository implements DetalleFacturasRepository {

    private final CrudDetalleFacturasEntity crudDetalleFacturasEntity;
    private final DetalleFacturasMapper detalleFacturasMapper;

    public DetalleFacturasEntityRepository(CrudDetalleFacturasEntity crudDetalleFacturasEntity, DetalleFacturasMapper detalleFacturasMapper) {
        this.crudDetalleFacturasEntity = crudDetalleFacturasEntity;
        this.detalleFacturasMapper = detalleFacturasMapper;
    }

    @Override
    public List<DetalleFacturasDto> obtenerTodos() {
        return detalleFacturasMapper.toDto((List<DetalleFacturas>) crudDetalleFacturasEntity.findAll());
    }

    @Override
    public Optional<DetalleFacturasDto> buscarPorId(Long idDetalleFactura) {
        return crudDetalleFacturasEntity.findById(idDetalleFactura)
                .map(detalleFacturasMapper::toDto);
    }

    @Override
    public List<DetalleFacturasDto> buscarPorIdFactura(Long idFactura) {
        return detalleFacturasMapper.toDto(crudDetalleFacturasEntity.findByIdFactura(idFactura));
    }

    @Override
    public DetalleFacturasDto guardar(DetalleFacturasDto detalleFacturasDto) {
        DetalleFacturas detalle = detalleFacturasMapper.toEntity(detalleFacturasDto);
        return detalleFacturasMapper.toDto(crudDetalleFacturasEntity.save(detalle));
    }

    @Override
    public Optional<DetalleFacturasDto> modificar(Long idDetalleFactura, ModDetalleFacturasDto modDto) {
        return crudDetalleFacturasEntity.findById(idDetalleFactura)
                .map(detalle -> {
                    detalle.setCantidad(modDto.cantidad());
                    detalle.setSubtotal(modDto.subtotal());
                    detalle.setIdFactura(modDto.idFactura());
                    detalle.setIdProducto(modDto.idProducto());
                    return detalleFacturasMapper.toDto(crudDetalleFacturasEntity.save(detalle));
                });
    }

    @Override
    public void eliminar(Long idDetalleFactura) {
        if (!crudDetalleFacturasEntity.existsById(idDetalleFactura)) {
            throw new DetalleFacturaNotExistsException(idDetalleFactura);
        }
        crudDetalleFacturasEntity.deleteById(idDetalleFactura);
    }
}
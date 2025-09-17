package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.dominio.repository.DetallePedidosRepository;
import org.asco_devs.kinalcoffeeshop.dominio.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.DetallePedidosNotExistsException;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudDetallePedidosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetallePedidosMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetallePedidosEntityRepository implements DetallePedidosRepository {

    private final CrudDetallePedidosEntity crudDetallePedidosEntity;
    private final DetallePedidosMapper detallePedidosMapper;

    public DetallePedidosEntityRepository(CrudDetallePedidosEntity crudDetallePedidosEntity,
                                         DetallePedidosMapper detallePedidosMapper) {
        this.crudDetallePedidosEntity = crudDetallePedidosEntity;
        this.detallePedidosMapper = detallePedidosMapper;
    }

    @Override
    public List<DetallePedidosDto> obtenerTodo() {
        return this.detallePedidosMapper.toDto(this.crudDetallePedidosEntity.findAll());
    }

    @Override
    public DetallePedidosDto buscarPorId(Long idDetalle) {
        return this.detallePedidosMapper.toDto(
                this.crudDetallePedidosEntity.findById(idDetalle).orElse(null)
        );
    }

    @Override
    public DetallePedidosDto guardarDetalle(DetallePedidosDto detalle) {
        DetallePedidosEntity entity = this.detallePedidosMapper.toEntity(detalle);
        this.crudDetallePedidosEntity.save(entity);
        return this.detallePedidosMapper.toDto(entity);
    }

    @Override
    public DetallePedidosDto modificarDetalle(Long idDetalle, ModDetallePedidosDto modDetalle) {
        DetallePedidosEntity entity = this.crudDetallePedidosEntity.findById(idDetalle).orElse(null);
        if (entity == null) {
            throw new DetallePedidosNotExistsException(idDetalle);
        }
        this.detallePedidosMapper.modificarEntityFromDto(modDetalle, entity);
        return this.detallePedidosMapper.toDto(this.crudDetallePedidosEntity.save(entity));
    }

    @Override
    public void eliminarDetalle(Long idDetalle) {
        DetallePedidosEntity entity = this.crudDetallePedidosEntity.findById(idDetalle).orElse(null);
        if (entity == null) {
            throw new DetallePedidosNotExistsException(idDetalle);
        }
        this.crudDetallePedidosEntity.deleteById(idDetalle);
    }
}
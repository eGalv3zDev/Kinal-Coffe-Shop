package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.repository.DetallePedidoRepository;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.detallePedido.DetallePedidoNotExistException;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudDetallePedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetallePedidoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetallePedidoEntityRepository implements DetallePedidoRepository {

    private final CrudDetallePedidoEntity crudDetallePedidoEntity;
    private final DetallePedidoMapper detallePedidoMapper;

    public DetallePedidoEntityRepository(CrudDetallePedidoEntity crudDetallePedidoEntity,
                                         DetallePedidoMapper detallePedidoMapper) {
        this.crudDetallePedidoEntity = crudDetallePedidoEntity;
        this.detallePedidoMapper = detallePedidoMapper;
    }

    @Override
    public List<DetallePedidoDto> obtenerTodo() {
        return this.detallePedidoMapper.toDto(this.crudDetallePedidoEntity.findAll());
    }

    @Override
    public DetallePedidoDto buscarPorId(Long idDetalle) {
        return this.detallePedidoMapper.toDto(
                this.crudDetallePedidoEntity.findById(idDetalle).orElse(null)
        );
    }

    @Override
    public DetallePedidoDto guardarDetalle(DetallePedidoDto detalle) {
        DetallePedidoEntity entity = this.detallePedidoMapper.toEntity(detalle);
        this.crudDetallePedidoEntity.save(entity);
        return this.detallePedidoMapper.toDto(entity);
    }

    @Override
    public DetallePedidoDto modificarDetalle(Long idDetalle, ModDetallePedidoDto modDetalle) {
        DetallePedidoEntity entity = this.crudDetallePedidoEntity.findById(idDetalle).orElse(null);
        if (entity == null) {
            throw new DetallePedidoNotExistException(idDetalle);
        }
        this.detallePedidoMapper.modificarEntityFromDto(modDetalle, entity);
        return this.detallePedidoMapper.toDto(this.crudDetallePedidoEntity.save(entity));
    }

    @Override
    public void eliminarDetalle(Long idDetalle) {
        DetallePedidoEntity entity = this.crudDetallePedidoEntity.findById(idDetalle).orElse(null);
        if (entity == null) {
            throw new DetallePedidoNotExistException(idDetalle);
        }
        this.crudDetallePedidoEntity.deleteById(idDetalle);
    }
}
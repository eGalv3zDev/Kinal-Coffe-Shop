package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.DetallePedidoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.DetallePedidosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudDetallePedidosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetallePedidosMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DetallePedidosEntityRepository implements DetallePedidosRepository {

    private final CrudDetallePedidosEntity crudDetallePedidosEntity;
    private final DetallePedidosMapper detallePedidosMapper;

    public DetallePedidosEntityRepository(CrudDetallePedidosEntity crudDetallePedidosEntity, DetallePedidosMapper detallePedidosMapper) {
        this.crudDetallePedidosEntity = crudDetallePedidosEntity;
        this.detallePedidosMapper = detallePedidosMapper;
    }

    @Override
    public List<DetallePedidosDto> obtenerTodos() {
        return detallePedidosMapper.toDto((List<DetallePedidos>) crudDetallePedidosEntity.findAll());
    }

    @Override
    public Optional<DetallePedidosDto> buscarPorId(Long idDetalle) {
        return crudDetallePedidosEntity.findById(idDetalle)
                .map(detallePedidosMapper::toDto);
    }

    @Override
    public List<DetallePedidosDto> buscarPorIdPedido(Long idPedido) {
        return detallePedidosMapper.toDto(crudDetallePedidosEntity.findByIdPedido(idPedido));
    }

    @Override
    public DetallePedidosDto guardar(DetallePedidosDto detallePedidosDto) {
        DetallePedidos detalle = detallePedidosMapper.toEntity(detallePedidosDto);
        return detallePedidosMapper.toDto(crudDetallePedidosEntity.save(detalle));
    }

    @Override
    public Optional<DetallePedidosDto> modificar(Long idDetalle, ModDetallePedidosDto modDto) {
        return crudDetallePedidosEntity.findById(idDetalle)
                .map(detalle -> {
                    detalle.setCantidad(modDto.cantidad());
                    detalle.setSubtotal(modDto.subtotal());
                    detalle.setIdPedido(modDto.idPedido());
                    detalle.setIdProducto(modDto.idProducto());
                    return detallePedidosMapper.toDto(crudDetallePedidosEntity.save(detalle));
                });
    }

    @Override
    public void eliminar(Long idDetalle) {
        if (!crudDetallePedidosEntity.existsById(idDetalle)) {
            throw new DetallePedidoNotExistsException(idDetalle);
        }
        crudDetallePedidosEntity.deleteById(idDetalle);
    }
}
package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModPedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.PedidoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.PedidosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudPedidosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PedidosMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidosEntityRepository implements PedidosRepository {

    private final CrudPedidosEntity crudPedidosEntity;
    private final PedidosMapper pedidosMapper;

    public PedidosEntityRepository(CrudPedidosEntity crudPedidosEntity, PedidosMapper pedidosMapper) {
        this.crudPedidosEntity = crudPedidosEntity;
        this.pedidosMapper = pedidosMapper;
    }

    @Override
    public List<PedidosDto> obtenerTodos() {
        return pedidosMapper.toDto((List<Pedidos>) crudPedidosEntity.findAll());
    }

    @Override
    public Optional<PedidosDto> buscarPorId(Long idPedido) {
        return crudPedidosEntity.findById(idPedido)
                .map(pedidosMapper::toDto);
    }

    @Override
    public PedidosDto guardar(PedidosDto pedidosDto) {
        Pedidos pedido = pedidosMapper.toEntity(pedidosDto);
        return pedidosMapper.toDto(crudPedidosEntity.save(pedido));
    }

    @Override
    public Optional<PedidosDto> modificar(Long idPedido, ModPedidosDto modDto) {
        return crudPedidosEntity.findById(idPedido)
                .map(pedido -> {
                    pedido.setTotal(modDto.total());
                    pedido.setEstado(modDto.estado());
                    pedido.setIdAlumno(modDto.idAlumno());
                    pedido.setIdUsuarioCredito(modDto.idUsuarioCredito());
                    return pedidosMapper.toDto(crudPedidosEntity.save(pedido));
                });
    }

    @Override
    public void eliminar(Long idPedido) {
        if (!crudPedidosEntity.existsById(idPedido)) {
            throw new PedidoNotExistsException(idPedido);
        }
        crudPedidosEntity.deleteById(idPedido);
    }
}
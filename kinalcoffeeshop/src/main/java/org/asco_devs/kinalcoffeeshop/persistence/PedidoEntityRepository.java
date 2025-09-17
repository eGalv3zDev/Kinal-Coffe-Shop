package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.dominio.dto.PedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.PedidoNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.PedidoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudPedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PedidoMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PedidoEntityRepository implements PedidoRepository {

    private final CrudPedidoEntity crudPedidoEntity;
    private final PedidoMapper pedidoMapper;

    public PedidoEntityRepository(CrudPedidoEntity crudPedidoEntity, PedidoMapper pedidoMapper) {
        this.crudPedidoEntity = crudPedidoEntity;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public List<PedidoDto> obtenerPedidos() {
        return this.pedidoMapper.toDto(this.crudPedidoEntity.findAll());
    }

    @Override
    public PedidoDto buscarPorId(Long idPedido) {
        return this.pedidoMapper.toDto(this.crudPedidoEntity.findById(idPedido).orElse(null));
    }

    @Override
    public PedidoDto guardarPedido(PedidoDto dto) {
        PedidoEntity pedidoEntity = this.pedidoMapper.toEntity(dto);
        pedidoEntity.setFecha(LocalDateTime.now());
        this.crudPedidoEntity.save(pedidoEntity);
        return this.pedidoMapper.toDto(pedidoEntity);
    }

    @Override
    public PedidoDto modificarPedido(Long idPedido, ModPedidoDto mod) {
        PedidoEntity pedidoEntity = this.crudPedidoEntity.findById(idPedido).orElse(null);
        if (pedidoEntity == null) {
            throw new PedidoNotExistException(idPedido);
        }
        this.pedidoMapper.modificarEntityFromDto(mod, pedidoEntity);
        return this.pedidoMapper.toDto(this.crudPedidoEntity.save(pedidoEntity));
    }

    @Override
    public void eliminarPedido(Long idPedido) {
        if (!this.crudPedidoEntity.existsById(idPedido)) {
            throw new PedidoNotExistException(idPedido);
        }
        this.crudPedidoEntity.deleteById(idPedido);
    }
}
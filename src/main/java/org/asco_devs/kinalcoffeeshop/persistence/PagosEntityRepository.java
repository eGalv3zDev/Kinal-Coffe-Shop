package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModPagosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PagosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.PagoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.PagosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudPagosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pagos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PagosMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PagosEntityRepository implements PagosRepository {

    private final CrudPagosEntity crudPagosEntity;
    private final PagosMapper pagosMapper;

    public PagosEntityRepository(CrudPagosEntity crudPagosEntity, PagosMapper pagosMapper) {
        this.crudPagosEntity = crudPagosEntity;
        this.pagosMapper = pagosMapper;
    }

    @Override
    public List<PagosDto> obtenerTodos() {
        return pagosMapper.toDto((List<Pagos>) crudPagosEntity.findAll());
    }

    @Override
    public Optional<PagosDto> buscarPorId(Long idPago) {
        return crudPagosEntity.findById(idPago)
                .map(pagosMapper::toDto);
    }

    @Override
    public List<PagosDto> buscarPorIdPedido(Long idPedido) {
        return pagosMapper.toDto(crudPagosEntity.findByIdPedido(idPedido));
    }

    @Override
    public PagosDto guardar(PagosDto pagosDto) {
        Pagos pago = pagosMapper.toEntity(pagosDto);
        return pagosMapper.toDto(crudPagosEntity.save(pago));
    }

    @Override
    public Optional<PagosDto> modificar(Long idPago, ModPagosDto modDto) {
        return crudPagosEntity.findById(idPago)
                .map(pago -> {
                    pago.setMonto(modDto.monto());
                    pago.setTipo(modDto.tipo());
                    pago.setIdPedido(modDto.idPedido());
                    return pagosMapper.toDto(crudPagosEntity.save(pago));
                });
    }

    @Override
    public void eliminar(Long idPago) {
        if (!crudPagosEntity.existsById(idPago)) {
            throw new PagoNotExistsException(idPago);
        }
        crudPagosEntity.deleteById(idPago);
    }
}
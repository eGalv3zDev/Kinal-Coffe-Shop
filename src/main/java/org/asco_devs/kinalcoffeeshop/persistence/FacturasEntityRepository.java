package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.FacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.FacturaExistsException;
import org.asco_devs.kinalcoffeeshop.domain.exception.FacturaNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.FacturasRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudFacturasEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Facturas;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.FacturasMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class FacturasEntityRepository implements FacturasRepository {

    private final CrudFacturasEntity crudFacturasEntity;
    private final FacturasMapper facturasMapper;

    public FacturasEntityRepository(CrudFacturasEntity crudFacturasEntity, FacturasMapper facturasMapper) {
        this.crudFacturasEntity = crudFacturasEntity;
        this.facturasMapper = facturasMapper;
    }

    @Override
    public List<FacturasDto> obtenerTodas() {
        return facturasMapper.toDto((List<Facturas>) crudFacturasEntity.findAll());
    }

    @Override
    public Optional<FacturasDto> buscarPorId(Long idFactura) {
        return crudFacturasEntity.findById(idFactura)
                .map(facturasMapper::toDto);
    }

    @Override
    public Optional<FacturasDto> buscarPorIdPedido(Long idPedido) {
        return crudFacturasEntity.findByIdPedido(idPedido)
                .map(facturasMapper::toDto);
    }

    @Override
    public FacturasDto guardar(FacturasDto facturasDto) {
        crudFacturasEntity.findByIdPedido(facturasDto.idPedido())
                .ifPresent(f -> {
                    throw new FacturaExistsException(f.getIdPedido());
                });
        Facturas factura = facturasMapper.toEntity(facturasDto);
        return facturasMapper.toDto(crudFacturasEntity.save(factura));
    }

    @Override
    public Optional<FacturasDto> modificar(Long idFactura, ModFacturasDto modDto) {
        return crudFacturasEntity.findById(idFactura)
                .map(factura -> {
                    factura.setTotal(modDto.total());
                    factura.setIdPedido(modDto.idPedido());
                    return facturasMapper.toDto(crudFacturasEntity.save(factura));
                });
    }

    @Override
    public void eliminar(Long idFactura) {
        if (!crudFacturasEntity.existsById(idFactura)) {
            throw new FacturaNotExistsException(idFactura);
        }
        crudFacturasEntity.deleteById(idFactura);
    }
}
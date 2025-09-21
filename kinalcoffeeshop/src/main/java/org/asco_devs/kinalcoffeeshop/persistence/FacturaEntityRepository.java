package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.dominio.dto.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.FacturaAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.FacturaNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.FacturaRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudFacturaEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.FacturasEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.FacturaMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FacturaEntityRepository implements FacturaRepository {

    private final CrudFacturaEntity crudFacturaEntity;
    private final FacturaMapper facturaMapper;

    public FacturaEntityRepository(CrudFacturaEntity crudFacturaEntity, FacturaMapper facturaMapper) {
        this.crudFacturaEntity = crudFacturaEntity;
        this.facturaMapper = facturaMapper;
    }

    @Override
    public List<FacturaDto> obtenerFacturas() {
        return this.facturaMapper.toDto(this.crudFacturaEntity.findAll());
    }

    @Override
    public FacturaDto buscarFacturaPorId(Long idFactura) {
        return this.facturaMapper.toDto(this.crudFacturaEntity.findById(idFactura).orElse(null));
    }

    @Override
    public FacturaDto guardarFactura(FacturaDto dto) {
        if(this.crudFacturaEntity.findById(dto.id()) != null){
            throw new FacturaAlreadyExistException(dto.id().toString());
        }
        FacturasEntity facturaEntity = this.facturaMapper.toEntity(dto);
        facturaEntity.setFecha(LocalDateTime.now());
        this.crudFacturaEntity.save(facturaEntity);
        return facturaMapper.toDto(facturaEntity);
    }

    @Override
    public FacturaDto modificarFactura(Long idFactura, ModFacturaDto mod) {
        FacturasEntity facturaEntity = this.crudFacturaEntity.findById(idFactura).orElse(null);
        if(facturaEntity == null){
            throw new FacturaNotExistException(idFactura);
        }
        this.facturaMapper.modificarEntityFromDto(mod, facturaEntity);
        return facturaMapper.toDto(this.crudFacturaEntity.save(facturaEntity));
    }

    @Override
    public void eliminarFactura(Long idFactura) {
        FacturasEntity facturaEntity = this.crudFacturaEntity.findById(idFactura).orElse(null);
        if(facturaEntity == null){
            throw new FacturaNotExistException(idFactura);
        }
        else {
            this.crudFacturaEntity.deleteById(idFactura);
        }
    }
}
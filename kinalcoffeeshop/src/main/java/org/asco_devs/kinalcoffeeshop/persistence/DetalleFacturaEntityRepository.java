package org.asco_devs.kinalcoffeeshop.persistence;


import org.asco_devs.kinalcoffeeshop.dominio.dto.DetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.DetalleFacturaAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.DetalleFacturaNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.DetalleFacturaRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudDetalleFacturaEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturaEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetalleFacturaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetalleFacturaEntityRepository implements DetalleFacturaRepository {

    private final CrudDetalleFacturaEntity crudDetalleFacturaEntity;
    private final DetalleFacturaMapper detalleFacturaMapper;

    public DetalleFacturaEntityRepository(CrudDetalleFacturaEntity crudDetalleFacturaEntity, DetalleFacturaMapper detalleFacturaMapper) {
        this.crudDetalleFacturaEntity = crudDetalleFacturaEntity;
        this.detalleFacturaMapper = detalleFacturaMapper;
    }

    @Override
    public List<DetalleFacturaDto> obtenerDetalleFactura() {
        return this.detalleFacturaMapper.toDto(this.crudDetalleFacturaEntity.findAll());
    }

    @Override
    public DetalleFacturaDto buscarPorId(Long idDetalleFactura) {
        return this.detalleFacturaMapper.toDto(this.crudDetalleFacturaEntity.findById(idDetalleFactura).orElse(null));
    }

    @Override
    public DetalleFacturaDto guardarDetalleFactura(DetalleFacturaDto dto) {
        if(this.crudDetalleFacturaEntity.findFirstByIdDetalleFactura(dto.id()) != null){
            throw new DetalleFacturaAlreadyExistException(dto.id());
        }
        DetalleFacturaEntity detalleFacturaEntity = this.detalleFacturaMapper.toEntity(dto);
        this.crudDetalleFacturaEntity.save(detalleFacturaEntity);
        return this.detalleFacturaMapper.toDto(detalleFacturaEntity);
    }

    @Override
    public DetalleFacturaDto modificarDetalleFactura(Long idDetalleFactura, ModDetalleFacturaDto mod) {
        DetalleFacturaEntity detalleFacturaEntity = this.crudDetalleFacturaEntity.findById(idDetalleFactura).orElse(null);
        if(detalleFacturaEntity == null){
            throw new DetalleFacturaNotExistException(idDetalleFactura);
        }
        this.detalleFacturaMapper.modificarEntityFromDto(mod, detalleFacturaEntity);
        return this.detalleFacturaMapper.toDto(this.crudDetalleFacturaEntity.save(detalleFacturaEntity));
    }

    @Override
    public void eliminarDetalleFactura(Long idDetalleFactura) {
        DetalleFacturaEntity detalleFacturaEntity = this.crudDetalleFacturaEntity.findById(idDetalleFactura).orElse(null);
        if(detalleFacturaEntity == null){
            throw new DetalleFacturaNotExistException(idDetalleFactura);
        }
        else {
            this.crudDetalleFacturaEntity.deleteById(idDetalleFactura);
        }
    }
}
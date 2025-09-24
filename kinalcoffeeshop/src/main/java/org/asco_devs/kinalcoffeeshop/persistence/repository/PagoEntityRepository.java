package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.ModPagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.pago.PagoNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.PagoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudPagoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PagoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PagoMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PagoEntityRepository implements PagoRepository {

    private final CrudPagoEntity crudPagoEntity;
    private final PagoMapper pagoMapper;

    public PagoEntityRepository(CrudPagoEntity crudPagoEntity, PagoMapper pagoMapper) {
        this.crudPagoEntity = crudPagoEntity;
        this.pagoMapper = pagoMapper;
    }

    @Override
    public List<PagoDto> obtenerPagos() {
        return this.pagoMapper.toDto(this.crudPagoEntity.findAll());
    }

    @Override
    public PagoDto buscarPorId(Long idPago) {
        return this.pagoMapper.toDto(this.crudPagoEntity.findById(idPago).orElse(null));
    }

    @Override
    public PagoDto guardarPago(PagoDto dto) {
        PagoEntity pagoEntity = this.pagoMapper.toEntity(dto);
        pagoEntity.setFecha(LocalDateTime.now());
        this.crudPagoEntity.save(pagoEntity);
        return this.pagoMapper.toDto(pagoEntity);
    }

    @Override
    public PagoDto modificarPago(Long idPago, ModPagoDto mod) {
        PagoEntity pagoEntity = this.crudPagoEntity.findById(idPago).orElse(null);
        if (pagoEntity == null) {
            throw new PagoNotExistException(idPago);
        }
        this.pagoMapper.modificarEntityFromDto(mod, pagoEntity);
        return this.pagoMapper.toDto(this.crudPagoEntity.save(pagoEntity));
    }

    @Override
    public void eliminarPago(Long idPago) {
        if (!this.crudPagoEntity.existsById(idPago)) {
            throw new PagoNotExistException(idPago);
        }
        this.crudPagoEntity.deleteById(idPago);
    }
}
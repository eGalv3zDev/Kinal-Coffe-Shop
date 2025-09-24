package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito.HistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito.ModHistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.cuentaDeCredito.CreditHistoryNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.HistorialCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudHistorialCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.HistorialCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.HistorialCreditoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistorialCreditoEntityRepository implements HistorialCreditoRepository {

    private final CrudHistorialCreditoEntity crud;
    private final HistorialCreditoMapper mapper;

    public HistorialCreditoEntityRepository(CrudHistorialCreditoEntity crud, HistorialCreditoMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }


    @Override
    public List<HistorialCreditoDto> obtenerHistorialesCredito() {
        return this.mapper.toDto(this.crud.findAll());
    }

    @Override
    public HistorialCreditoDto buscarPorId(Long idHistorialCredito) {
        return this.mapper.toDto(this.crud.findById(idHistorialCredito).orElse(null));
    }

    @Override
    public HistorialCreditoDto guardarHistorialCredito(HistorialCreditoDto dto) {
        HistorialCreditoEntity historialCreditoEntity = this.mapper.toEntity(dto);
        this.crud.save(historialCreditoEntity);
        return this.mapper.toDto(historialCreditoEntity);
    }

    @Override
    public HistorialCreditoDto modificarHistorialCredito(Long idHistorialCredito, ModHistorialCreditoDto mod) {
        HistorialCreditoEntity entity = this.crud.findById(idHistorialCredito).orElse(null);
        if(entity != null){
            throw new CreditHistoryNotExistException(idHistorialCredito);
        }
        this.mapper.modificarEntityFromDto(mod, entity);
        return this.mapper.toDto(this.crud.save(entity));
    }

    @Override
    public void eliminarHistorialCredito(Long idHistorialCredito) {
        HistorialCreditoEntity entity = this.crud.findById(idHistorialCredito).orElse(null);
        if(entity != null){
            throw new CreditHistoryNotExistException(idHistorialCredito);
        }else {
            this.crud.deleteById(idHistorialCredito);
        }
    }
}

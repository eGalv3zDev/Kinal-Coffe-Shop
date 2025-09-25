package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.HistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModHistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.HistorialCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.HistorialCreditosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudHistorialCreditosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.HistorialCreditos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.HistorialCreditosMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class HistorialCreditosEntityRepository implements HistorialCreditosRepository {

    private final CrudHistorialCreditosEntity crudHistorialCreditosEntity;
    private final HistorialCreditosMapper historialCreditosMapper;

    public HistorialCreditosEntityRepository(CrudHistorialCreditosEntity crudHistorialCreditosEntity, HistorialCreditosMapper historialCreditosMapper) {
        this.crudHistorialCreditosEntity = crudHistorialCreditosEntity;
        this.historialCreditosMapper = historialCreditosMapper;
    }

    @Override
    public List<HistorialCreditosDto> obtenerTodos() {
        return historialCreditosMapper.toDto((List<HistorialCreditos>) crudHistorialCreditosEntity.findAll());
    }

    @Override
    public Optional<HistorialCreditosDto> buscarPorId(Long idHistorial) {
        return crudHistorialCreditosEntity.findById(idHistorial)
                .map(historialCreditosMapper::toDto);
    }

    @Override
    public List<HistorialCreditosDto> buscarPorIdCuenta(Long idCuenta) {
        return historialCreditosMapper.toDto(crudHistorialCreditosEntity.findByIdCuenta(idCuenta));
    }

    @Override
    public HistorialCreditosDto guardar(HistorialCreditosDto historialCreditosDto) {
        HistorialCreditos historial = historialCreditosMapper.toEntity(historialCreditosDto);
        return historialCreditosMapper.toDto(crudHistorialCreditosEntity.save(historial));
    }

    @Override
    public Optional<HistorialCreditosDto> modificar(Long idHistorial, ModHistorialCreditosDto modDto) {
        return crudHistorialCreditosEntity.findById(idHistorial)
                .map(historial -> {
                    historial.setIdCuenta(modDto.idCuenta());
                    historial.setTipoMovimiento(modDto.tipoMovimiento());
                    historial.setMonto(modDto.monto());
                    historial.setIdConsumo(modDto.idConsumo());
                    historial.setIdPago(modDto.idPago());
                    return historialCreditosMapper.toDto(crudHistorialCreditosEntity.save(historial));
                });
    }

    @Override
    public void eliminar(Long idHistorial) {
        if (!crudHistorialCreditosEntity.existsById(idHistorial)) {
            throw new HistorialCreditoNotExistsException(idHistorial);
        }
        crudHistorialCreditosEntity.deleteById(idHistorial);
    }
}
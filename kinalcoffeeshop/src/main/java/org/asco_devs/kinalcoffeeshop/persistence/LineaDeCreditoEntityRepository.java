package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.dominio.dto.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModLineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.LineaDeCreditoNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.LineaDeCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudLineaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.LineaDeCreditoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LineaDeCreditoEntityRepository implements LineaDeCreditoRepository {
    private final CrudLineaDeCreditoEntity crud;
    private final LineaDeCreditoMapper mapper;

    public LineaDeCreditoEntityRepository(CrudLineaDeCreditoEntity crud, LineaDeCreditoMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public List<LineaDeCreditoDto> obtenerLineasDeCredito() {
        return this.mapper.toDto(this.crud.findAll());
    }

    @Override
    public LineaDeCreditoDto buscarPorId(Long idConsumo) {
        return this.mapper.toDto(this.crud.findById(idConsumo).orElse(null));
    }

    @Override
    public LineaDeCreditoDto guardarLineaDeCredito(LineaDeCreditoDto dto) {
        LineaDeCreditoEntity entity = this.mapper.toEntity(dto);
        this.crud.save(entity);
        return this.mapper.toDto(entity);
    }

    @Override
    public LineaDeCreditoDto modificarLineaDeCredito(Long idConsumo, ModLineaDeCreditoDto mod) {
        Optional<LineaDeCreditoEntity> entityOptional = this.crud.findById(idConsumo);
        if (entityOptional.isEmpty()) {
            throw new LineaDeCreditoNotExistException(idConsumo);
        }
        LineaDeCreditoEntity entity = entityOptional.get();
        this.mapper.modificarEntityFromDto(mod, entity);
        return this.mapper.toDto(this.crud.save(entity));
    }

    @Override
    public void eliminarLineaDeCredito(Long idConsumo) {
        if (this.crud.existsById(idConsumo)) {
            this.crud.deleteById(idConsumo);
        } else {
            throw new LineaDeCreditoNotExistException(idConsumo);
        }
    }
}
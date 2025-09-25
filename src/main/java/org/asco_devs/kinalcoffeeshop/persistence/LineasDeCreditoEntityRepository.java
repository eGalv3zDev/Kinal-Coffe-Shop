package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.LineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModLineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.LineaDeCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.LineasDeCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudLineasDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineasDeCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.LineasDeCreditoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LineasDeCreditoEntityRepository implements LineasDeCreditoRepository {

    private final CrudLineasDeCreditoEntity crudLineasDeCreditoEntity;
    private final LineasDeCreditoMapper lineasDeCreditoMapper;

    public LineasDeCreditoEntityRepository(CrudLineasDeCreditoEntity crudLineasDeCreditoEntity, LineasDeCreditoMapper lineasDeCreditoMapper) {
        this.crudLineasDeCreditoEntity = crudLineasDeCreditoEntity;
        this.lineasDeCreditoMapper = lineasDeCreditoMapper;
    }

    @Override
    public List<LineasDeCreditoDto> obtenerTodas() {
        return lineasDeCreditoMapper.toDto((List<LineasDeCredito>) crudLineasDeCreditoEntity.findAll());
    }

    @Override
    public Optional<LineasDeCreditoDto> buscarPorId(Long idConsumo) {
        return crudLineasDeCreditoEntity.findById(idConsumo)
                .map(lineasDeCreditoMapper::toDto);
    }

    @Override
    public List<LineasDeCreditoDto> buscarPorIdCuenta(Long idCuenta) {
        return lineasDeCreditoMapper.toDto(crudLineasDeCreditoEntity.findByIdCuenta(idCuenta));
    }

    @Override
    public LineasDeCreditoDto guardar(LineasDeCreditoDto lineasDeCreditoDto) {
        LineasDeCredito linea = lineasDeCreditoMapper.toEntity(lineasDeCreditoDto);
        return lineasDeCreditoMapper.toDto(crudLineasDeCreditoEntity.save(linea));
    }

    @Override
    public Optional<LineasDeCreditoDto> modificar(Long idConsumo, ModLineasDeCreditoDto modDto) {
        return crudLineasDeCreditoEntity.findById(idConsumo)
                .map(linea -> {
                    linea.setCantidad(modDto.cantidad());
                    linea.setSubtotal(modDto.subtotal());
                    linea.setIdProducto(modDto.idProducto());
                    linea.setIdCuenta(modDto.idCuenta());
                    return lineasDeCreditoMapper.toDto(crudLineasDeCreditoEntity.save(linea));
                });
    }

    @Override
    public void eliminar(Long idConsumo) {
        if (!crudLineasDeCreditoEntity.existsById(idConsumo)) {
            throw new LineaDeCreditoNotExistsException(idConsumo);
        }
        crudLineasDeCreditoEntity.deleteById(idConsumo);
    }
}
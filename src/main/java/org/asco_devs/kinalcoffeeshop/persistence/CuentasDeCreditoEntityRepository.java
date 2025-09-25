package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.CuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.CuentaDeCreditoExistsException;
import org.asco_devs.kinalcoffeeshop.domain.exception.CuentaDeCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.CuentasDeCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudCuentasDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentasDeCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CuentasDeCreditoMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class CuentasDeCreditoEntityRepository implements CuentasDeCreditoRepository {

    private final CrudCuentasDeCreditoEntity crudCuentasDeCreditoEntity;
    private final CuentasDeCreditoMapper cuentasDeCreditoMapper;

    public CuentasDeCreditoEntityRepository(CrudCuentasDeCreditoEntity crudCuentasDeCreditoEntity, CuentasDeCreditoMapper cuentasDeCreditoMapper) {
        this.crudCuentasDeCreditoEntity = crudCuentasDeCreditoEntity;
        this.cuentasDeCreditoMapper = cuentasDeCreditoMapper;
    }

    @Override
    public List<CuentasDeCreditoDto> obtenerTodas() {
        return cuentasDeCreditoMapper.toDto((List<CuentasDeCredito>) crudCuentasDeCreditoEntity.findAll());
    }

    @Override
    public Optional<CuentasDeCreditoDto> buscarPorId(Long idCuenta) {
        return crudCuentasDeCreditoEntity.findById(idCuenta)
                .map(cuentasDeCreditoMapper::toDto);
    }

    @Override
    public Optional<CuentasDeCreditoDto> buscarPorIdUsuario(Long idUsuarioCredito) {
        return crudCuentasDeCreditoEntity.findByIdUsuarioCredito(idUsuarioCredito)
                .map(cuentasDeCreditoMapper::toDto);
    }

    @Override
    public CuentasDeCreditoDto guardar(CuentasDeCreditoDto cuentasDeCreditoDto) {
        crudCuentasDeCreditoEntity.findByIdUsuarioCredito(cuentasDeCreditoDto.idUsuarioCredito())
                .ifPresent(c -> {
                    throw new CuentaDeCreditoExistsException(c.getIdUsuarioCredito());
                });
        CuentasDeCredito cuenta = cuentasDeCreditoMapper.toEntity(cuentasDeCreditoDto);
        return cuentasDeCreditoMapper.toDto(crudCuentasDeCreditoEntity.save(cuenta));
    }

    @Override
    public Optional<CuentasDeCreditoDto> modificar(Long idCuenta, ModCuentasDeCreditoDto modDto) {
        return crudCuentasDeCreditoEntity.findById(idCuenta)
                .map(cuenta -> {
                    cuenta.setSaldo(modDto.saldo());
                    cuenta.setIdUsuarioCredito(modDto.idUsuarioCredito());
                    return cuentasDeCreditoMapper.toDto(crudCuentasDeCreditoEntity.save(cuenta));
                });
    }

    @Override
    public void eliminar(Long idCuenta) {
        if (!crudCuentasDeCreditoEntity.existsById(idCuenta)) {
            throw new CuentaDeCreditoNotExistsException(idCuenta);
        }
        crudCuentasDeCreditoEntity.deleteById(idCuenta);
    }
}
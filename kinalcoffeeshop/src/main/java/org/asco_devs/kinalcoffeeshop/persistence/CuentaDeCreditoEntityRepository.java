package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.dominio.dto.CuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModCuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.CreditAccountNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.CuentaDeCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudCuentaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CuentaDeCreditoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CuentaDeCreditoEntityRepository implements CuentaDeCreditoRepository {
    private final CrudCuentaDeCreditoEntity crud;
    private final CuentaDeCreditoMapper mapper;

    public CuentaDeCreditoEntityRepository(CrudCuentaDeCreditoEntity crud, CuentaDeCreditoMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public List<CuentaDeCreditoDto> obtenerCuentasDeCredito() {
        return this.mapper.toDto(this.crud.findAll());
    }

    @Override
    public CuentaDeCreditoDto buscarPorId(Long idCuenta) {
        return this.mapper.toDto(this.crud.findById(idCuenta).orElse(null));
    }

    @Override
    public CuentaDeCreditoDto guardarCuentaDeCredito(CuentaDeCreditoDto dto) {
        //if(this.crud.findFirstByIdUsuario(dto.idUser()!=null));{
            // throw new UserAlreadyHasCreditAccountException(dto.idUser().getIdUsuario());
        //}
        CuentaDeCreditoEntity entity = this.mapper.toEntity(dto);
        this.crud.save(entity);
        return this.mapper.toDto(entity);
    }

    @Override
    public CuentaDeCreditoDto modificarCuentaDeCredito(Long idCuenta, ModCuentaDeCreditoDto mod) {
        CuentaDeCreditoEntity entity = this.crud.findById(idCuenta).orElse(null);
        if(entity == null){
            throw new CreditAccountNotExistException(idCuenta);
        }
        this.mapper.modificarEntityFromDto(mod, entity);
        return this.mapper.toDto(this.crud.save(entity));
    }

    @Override
    public void eliminarCuentaDeCredito(Long idCuenta) {
        CuentaDeCreditoEntity entity = this.crud.findById(idCuenta).orElse(null);
        if (entity == null) {
            throw new CreditAccountNotExistException(idCuenta);
        } else {
            this.crud.deleteById(idCuenta);
        }
    }
}

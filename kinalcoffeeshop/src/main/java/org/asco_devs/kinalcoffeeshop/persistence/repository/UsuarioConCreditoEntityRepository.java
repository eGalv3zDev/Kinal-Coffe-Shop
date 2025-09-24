package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.usuariosConCredito.UsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.usuariosConCredito.ModUsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.usuarioConCredito.UsuarioConCreditoAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.usuarioConCredito.UsuarioConCreditoNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.UsuarioConCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudUsuarioConCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.UsuarioConCreditoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioConCreditoEntityRepository implements UsuarioConCreditoRepository {
    private final CrudUsuarioConCreditoEntity crudUsuarioConCreditoEntity;
    private final UsuarioConCreditoMapper usuarioConCreditoMapper;

    public UsuarioConCreditoEntityRepository(CrudUsuarioConCreditoEntity crudUsuarioConCreditoEntity, UsuarioConCreditoMapper usuarioConCreditoMapper) {
        this.crudUsuarioConCreditoEntity = crudUsuarioConCreditoEntity;
        this.usuarioConCreditoMapper = usuarioConCreditoMapper;
    }

    @Override
    public List<UsuarioConCreditoDto> obtenerUsuariosConCredito() {
        return this.usuarioConCreditoMapper.toDto(this.crudUsuarioConCreditoEntity.findAll());
    }

    @Override
    public UsuarioConCreditoDto buscarPorId(Long idUsuarioCredito) {
        return this.usuarioConCreditoMapper.toDto(this.crudUsuarioConCreditoEntity.findById(idUsuarioCredito).orElse(null));
    }

    @Override
    public UsuarioConCreditoDto guardarUsuarioConCredito(UsuarioConCreditoDto dto) {
        if(this.crudUsuarioConCreditoEntity.findFirstByCorreo(dto.email()) != null) {
            throw new UsuarioConCreditoAlreadyExistException(dto.email());
        }
        UsuarioConCreditoEntity usuario = this.usuarioConCreditoMapper.toEntity(dto);
        this.crudUsuarioConCreditoEntity.save(usuario);
        return this.usuarioConCreditoMapper.toDto(usuario);
    }

    @Override
    public UsuarioConCreditoDto modificarUsuarioConCredito(Long idUsuarioCredito, ModUsuarioConCreditoDto mod) {
        UsuarioConCreditoEntity usuarioConCreditoEntity = this.crudUsuarioConCreditoEntity.findById(idUsuarioCredito).orElse(null);
        if(usuarioConCreditoEntity == null) {
            throw new UsuarioConCreditoNotExistException(String.valueOf(idUsuarioCredito));
        }
        this.usuarioConCreditoMapper.modificarEntityFromDto(mod, usuarioConCreditoEntity);
        return this.usuarioConCreditoMapper.toDto(this.crudUsuarioConCreditoEntity.save(usuarioConCreditoEntity));
    }

    @Override
    public void eliminarUsuarioConCredito(Long idUsuarioCredito) {
        UsuarioConCreditoEntity usuarioConCreditoEntity = this.crudUsuarioConCreditoEntity.findById(idUsuarioCredito).orElse(null);
        if(usuarioConCreditoEntity == null) {
            throw new UsuarioConCreditoNotExistException(String.valueOf(idUsuarioCredito));
        } else {
            this.crudUsuarioConCreditoEntity.deleteById(idUsuarioCredito);
        }
    }
}
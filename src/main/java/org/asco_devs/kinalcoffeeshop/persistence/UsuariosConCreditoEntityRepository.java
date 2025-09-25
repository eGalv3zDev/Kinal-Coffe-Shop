package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModUsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.UsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.UsuarioConCreditoExistsException;
import org.asco_devs.kinalcoffeeshop.domain.exception.UsuarioConCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.UsuariosConCreditoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudUsuariosConCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.UsuariosConCreditoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuariosConCreditoEntityRepository implements UsuariosConCreditoRepository {

    private final CrudUsuariosConCreditoEntity crudUsuariosConCreditoEntity;
    private final UsuariosConCreditoMapper usuariosConCreditoMapper;

    public UsuariosConCreditoEntityRepository(CrudUsuariosConCreditoEntity crudUsuariosConCreditoEntity, UsuariosConCreditoMapper usuariosConCreditoMapper) {
        this.crudUsuariosConCreditoEntity = crudUsuariosConCreditoEntity;
        this.usuariosConCreditoMapper = usuariosConCreditoMapper;
    }

    @Override
    public List<UsuariosConCreditoDto> obtenerTodos() {
        return usuariosConCreditoMapper.toDto((List<UsuariosConCredito>) crudUsuariosConCreditoEntity.findAll());
    }

    @Override
    public Optional<UsuariosConCreditoDto> buscarPorId(Long idUsuarioCredito) {
        return crudUsuariosConCreditoEntity.findById(idUsuarioCredito)
                .map(usuariosConCreditoMapper::toDto);
    }

    @Override
    public UsuariosConCreditoDto guardar(UsuariosConCreditoDto usuariosConCreditoDto) {
        if (usuariosConCreditoDto.correo() != null) {
            crudUsuariosConCreditoEntity.findByCorreo(usuariosConCreditoDto.correo())
                    .ifPresent(u -> {
                        throw new UsuarioConCreditoExistsException(u.getCorreo());
                    });
        }
        UsuariosConCredito usuario = usuariosConCreditoMapper.toEntity(usuariosConCreditoDto);
        return usuariosConCreditoMapper.toDto(crudUsuariosConCreditoEntity.save(usuario));
    }

    @Override
    public Optional<UsuariosConCreditoDto> modificar(Long idUsuarioCredito, ModUsuariosConCreditoDto modDto) {
        return crudUsuariosConCreditoEntity.findById(idUsuarioCredito)
                .map(usuario -> {
                    usuario.setNombre(modDto.nombre());
                    usuario.setApellido(modDto.apellido());
                    usuario.setTelefono(modDto.telefono());
                    usuario.setCorreo(modDto.correo());
                    usuario.setGenero(modDto.genero());
                    usuario.setFechaNacimiento(modDto.fechaNacimiento());
                    usuario.setContrasena(modDto.contrasena());
                    return usuariosConCreditoMapper.toDto(crudUsuariosConCreditoEntity.save(usuario));
                });
    }

    @Override
    public void eliminar(Long idUsuarioCredito) {
        if (!crudUsuariosConCreditoEntity.existsById(idUsuarioCredito)) {
            throw new UsuarioConCreditoNotExistsException(idUsuarioCredito);
        }
        crudUsuariosConCreditoEntity.deleteById(idUsuarioCredito);
    }
}
package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModUsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.UsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.UsuarioConCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.UsuariosConCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosConCreditoService {

    private final UsuariosConCreditoRepository usuariosConCreditoRepository;

    public UsuariosConCreditoService(UsuariosConCreditoRepository usuariosConCreditoRepository) {
        this.usuariosConCreditoRepository = usuariosConCreditoRepository;
    }

    public List<UsuariosConCreditoDto> obtenerTodo() {
        return usuariosConCreditoRepository.obtenerTodos();
    }

    public UsuariosConCreditoDto buscarPorCodigo(Long idUsuarioCredito) {
        return usuariosConCreditoRepository.buscarPorId(idUsuarioCredito)
                .orElseThrow(() -> new UsuarioConCreditoNotExistsException(idUsuarioCredito));
    }

    public UsuariosConCreditoDto guardarUsuario(UsuariosConCreditoDto usuariosConCreditoDto) {
        return usuariosConCreditoRepository.guardar(usuariosConCreditoDto);
    }

    public UsuariosConCreditoDto modificarUsuario(Long idUsuarioCredito, ModUsuariosConCreditoDto modDto) {
        return usuariosConCreditoRepository.modificar(idUsuarioCredito, modDto)
                .orElseThrow(() -> new UsuarioConCreditoNotExistsException(idUsuarioCredito));
    }

    public void eliminarUsuario(Long idUsuarioCredito) {
        usuariosConCreditoRepository.eliminar(idUsuarioCredito);
    }
}
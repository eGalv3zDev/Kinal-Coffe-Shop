package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.ModUsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.UsuariosConCreditoDto;

import java.util.List;
import java.util.Optional;

public interface UsuariosConCreditoRepository {
    List<UsuariosConCreditoDto> obtenerTodos();
    Optional<UsuariosConCreditoDto> buscarPorId(Long idUsuarioCredito);
    UsuariosConCreditoDto guardar(UsuariosConCreditoDto usuariosConCreditoDto);
    Optional<UsuariosConCreditoDto> modificar(Long idUsuarioCredito, ModUsuariosConCreditoDto modUsuariosConCreditoDto);
    void eliminar(Long idUsuarioCredito);
}
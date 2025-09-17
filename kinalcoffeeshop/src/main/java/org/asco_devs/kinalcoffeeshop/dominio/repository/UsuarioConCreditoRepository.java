package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.UsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModUsuarioConCreditoDto;

import java.util.List;

public interface UsuarioConCreditoRepository {
    List<UsuarioConCreditoDto> obtenerUsuariosConCredito();
    UsuarioConCreditoDto buscarPorId(Long idUsuarioCredito);
    UsuarioConCreditoDto guardarUsuarioConCredito(UsuarioConCreditoDto dto);
    UsuarioConCreditoDto modificarUsuarioConCredito(Long idUsuarioCredito, ModUsuarioConCreditoDto mod);
    void eliminarUsuarioConCredito(Long idUsuarioCredito);
}
package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.usuariosConCredito.UsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.usuariosConCredito.ModUsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.UsuarioConCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioConCreditoService {
    private final UsuarioConCreditoRepository usuarioConCreditoRepository;

    public UsuarioConCreditoService(UsuarioConCreditoRepository usuarioConCreditoRepository) {
        this.usuarioConCreditoRepository = usuarioConCreditoRepository;
    }

    public List<UsuarioConCreditoDto> obtenerUsuariosConCredito() {
        return this.usuarioConCreditoRepository.obtenerUsuariosConCredito();
    }

    public UsuarioConCreditoDto buscarCodigo(Long codigo){
        return this.usuarioConCreditoRepository.buscarPorId(codigo);
    }

    public UsuarioConCreditoDto guardarUsuarioConCredito(UsuarioConCreditoDto usuarioConCreditoDto){
        return this.usuarioConCreditoRepository.guardarUsuarioConCredito(usuarioConCreditoDto);
    }

    public UsuarioConCreditoDto modificarUsuarioConCredito(Long codigo, ModUsuarioConCreditoDto mod){
        return this.usuarioConCreditoRepository.modificarUsuarioConCredito(codigo, mod);
    }

    public void eliminarUsuarioConCredito(Long codigo){
        this.usuarioConCreditoRepository.eliminarUsuarioConCredito(codigo);
    }
}
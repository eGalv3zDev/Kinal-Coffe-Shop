package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito.CuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito.ModCuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.CuentaDeCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaDeCreditoService {
    private final CuentaDeCreditoRepository repository;

    public CuentaDeCreditoService(CuentaDeCreditoRepository repository) {
        this.repository = repository;
    }

    public List<CuentaDeCreditoDto> obtenerCuentasDeCredito() {
        return this.repository.obtenerCuentasDeCredito();
    }

    public CuentaDeCreditoDto buscarPorId(Long idCuenta) {
        return this.repository.buscarPorId(idCuenta);
    }

    public CuentaDeCreditoDto guardarCuentaDeCredito(CuentaDeCreditoDto dto) {
        return this.repository.guardarCuentaDeCredito(dto);
    }

    public CuentaDeCreditoDto modificarCuentaDeCredito(Long idCuenta, ModCuentaDeCreditoDto mod) {
        return this.repository.modificarCuentaDeCredito(idCuenta, mod);
    }

    public void eliminarCuentaDeCredito(Long idCuenta) {
        this.repository.eliminarCuentaDeCredito(idCuenta);
    }
}

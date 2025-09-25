package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito.CuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito.ModCuentaDeCreditoDto;

import java.util.List;

public interface CuentaDeCreditoRepository {
    List<CuentaDeCreditoDto> obtenerCuentasDeCredito();
    CuentaDeCreditoDto buscarPorId(Long idCuenta);
    CuentaDeCreditoDto guardarCuentaDeCredito(CuentaDeCreditoDto dto);
    CuentaDeCreditoDto modificarCuentaDeCredito(Long idCuenta, ModCuentaDeCreditoDto mod);
    void eliminarCuentaDeCredito(Long idCuenta);
}

package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.CuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCuentasDeCreditoDto;
import java.util.List;
import java.util.Optional;

public interface CuentasDeCreditoRepository {
    List<CuentasDeCreditoDto> obtenerTodas();

    Optional<CuentasDeCreditoDto> buscarPorId(Long idCuenta);

    Optional<CuentasDeCreditoDto> buscarPorIdUsuario(Long idUsuarioCredito);

    CuentasDeCreditoDto guardar(CuentasDeCreditoDto cuentasDeCreditoDto);

    Optional<CuentasDeCreditoDto> modificar(Long idCuenta, ModCuentasDeCreditoDto modCuentasDeCreditoDto);

    void eliminar(Long idCuenta);
}
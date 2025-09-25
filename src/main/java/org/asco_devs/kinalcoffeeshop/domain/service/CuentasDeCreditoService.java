package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.CuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.CuentaDeCreditoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.CuentasDeCreditoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CuentasDeCreditoService {

    private final CuentasDeCreditoRepository cuentasDeCreditoRepository;

    public CuentasDeCreditoService(CuentasDeCreditoRepository cuentasDeCreditoRepository) {
        this.cuentasDeCreditoRepository = cuentasDeCreditoRepository;
    }

    public List<CuentasDeCreditoDto> obtenerTodo() {
        return cuentasDeCreditoRepository.obtenerTodas();
    }

    public CuentasDeCreditoDto buscarPorCodigo(Long idCuenta) {
        return cuentasDeCreditoRepository.buscarPorId(idCuenta)
                .orElseThrow(() -> new CuentaDeCreditoNotExistsException(idCuenta));
    }

    public Optional<CuentasDeCreditoDto> buscarPorUsuario(Long idUsuarioCredito) {
        return cuentasDeCreditoRepository.buscarPorIdUsuario(idUsuarioCredito);
    }

    public CuentasDeCreditoDto guardarCuenta(CuentasDeCreditoDto cuentasDeCreditoDto) {
        return cuentasDeCreditoRepository.guardar(cuentasDeCreditoDto);
    }

    public CuentasDeCreditoDto modificarCuenta(Long idCuenta, ModCuentasDeCreditoDto modDto) {
        return cuentasDeCreditoRepository.modificar(idCuenta, modDto)
                .orElseThrow(() -> new CuentaDeCreditoNotExistsException(idCuenta));
    }

    public void eliminarCuenta(Long idCuenta) {
        cuentasDeCreditoRepository.eliminar(idCuenta);
    }
}
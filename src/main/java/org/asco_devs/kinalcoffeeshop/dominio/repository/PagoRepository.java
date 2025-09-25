package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.ModPagoDto;

import java.util.List;

public interface PagoRepository {

    List<PagoDto> obtenerPagos();
    PagoDto buscarPorId(Long idPago);
    PagoDto guardarPago(PagoDto dto);
    PagoDto modificarPago(Long idPago, ModPagoDto mod);
    void eliminarPago(Long idPago);
}
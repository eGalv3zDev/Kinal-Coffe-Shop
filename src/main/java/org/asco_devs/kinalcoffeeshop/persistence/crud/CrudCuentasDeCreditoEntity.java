package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentasDeCredito;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CrudCuentasDeCreditoEntity extends CrudRepository<CuentasDeCredito, Long> {
    Optional<CuentasDeCredito> findByIdUsuarioCredito(Long idUsuarioCredito);
}
package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.LineasDeCredito;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CrudLineasDeCreditoEntity extends CrudRepository<LineasDeCredito, Long> {
    List<LineasDeCredito> findByIdCuenta(Long idCuenta);
}
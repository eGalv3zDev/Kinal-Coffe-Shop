package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.HistorialCreditos;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CrudHistorialCreditosEntity extends CrudRepository<HistorialCreditos, Long> {
    List<HistorialCreditos> findByIdCuenta(Long idCuenta);
}
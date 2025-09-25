package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.FacturasEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudFacturaEntity extends CrudRepository<FacturasEntity, Long> {
}
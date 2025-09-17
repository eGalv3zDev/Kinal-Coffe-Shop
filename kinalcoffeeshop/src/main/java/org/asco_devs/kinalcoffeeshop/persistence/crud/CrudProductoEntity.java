package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudProductoEntity extends CrudRepository<ProductoEntity, Long> {
    ProductoEntity findFirstByNombre(String nombre);
}

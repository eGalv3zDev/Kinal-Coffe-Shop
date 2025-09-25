package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudCategoriaEntity extends CrudRepository<CategoriaEntity, Long> {
    CategoriaEntity findFirstByNombre(String nombre);
}

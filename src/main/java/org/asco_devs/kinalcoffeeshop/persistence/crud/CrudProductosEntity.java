package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudProductosEntity extends CrudRepository<Productos, Long> {
    Optional<Productos> findByNombre(String nombre);
}
package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaProductos;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudCategoriaProductosEntity extends CrudRepository<CategoriaProductos, Long> {
    Optional<CategoriaProductos> findByNombre(String nombre);
}
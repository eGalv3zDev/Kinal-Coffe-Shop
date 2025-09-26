package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudAlumnosEntity extends CrudRepository<Alumnos, Long> {
    Optional<Alumnos> findByCarnet(String carnet);
    Optional<Alumnos> findByCorreo(String correo);
}
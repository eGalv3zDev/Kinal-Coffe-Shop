package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.AlumnoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudAlumnoEntity extends CrudRepository<AlumnoEntity, Long> {
    AlumnoEntity findFirstByNombre(String nombre);
    AlumnoEntity findFirstByCarnet(String carnet);
}

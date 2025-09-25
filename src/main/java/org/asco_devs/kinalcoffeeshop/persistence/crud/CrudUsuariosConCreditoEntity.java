package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudUsuariosConCreditoEntity extends CrudRepository<UsuariosConCredito, Long> {
    Optional<UsuariosConCredito> findByCorreo(String correo);
}
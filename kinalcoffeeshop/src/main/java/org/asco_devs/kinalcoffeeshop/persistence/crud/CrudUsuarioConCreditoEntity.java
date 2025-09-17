package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudUsuarioConCreditoEntity extends CrudRepository<UsuarioConCreditoEntity, Long> {
    UsuarioConCreditoEntity findFirstByNombre(String nombre);
    UsuarioConCreditoEntity findFirstByCorreo(String correo);
}
package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.Facturas;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CrudFacturasEntity extends CrudRepository<Facturas, Long> {
    Optional<Facturas> findByIdPedido(Long idPedido);
}
package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturas;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CrudDetalleFacturasEntity extends CrudRepository<DetalleFacturas, Long> {
    List<DetalleFacturas> findByIdFactura(Long idFactura);
}
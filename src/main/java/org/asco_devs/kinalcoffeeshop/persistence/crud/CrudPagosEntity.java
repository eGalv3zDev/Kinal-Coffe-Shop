package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.Pagos;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CrudPagosEntity extends CrudRepository<Pagos, Long> {
    List<Pagos> findByIdPedido(Long idPedido);
}
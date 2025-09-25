package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidos;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CrudDetallePedidosEntity extends CrudRepository<DetallePedidos, Long> {
    List<DetallePedidos> findByIdPedido(Long idPedido);
}
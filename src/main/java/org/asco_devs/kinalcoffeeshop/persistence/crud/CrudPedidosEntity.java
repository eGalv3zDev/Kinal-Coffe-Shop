package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.springframework.data.repository.CrudRepository;

public interface CrudPedidosEntity extends CrudRepository<Pedidos, Long> {
}
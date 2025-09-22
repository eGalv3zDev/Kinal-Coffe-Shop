package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudDetalleFacturaEntity extends CrudRepository<DetalleFacturaEntity, Long> {
    DetalleFacturaEntity findFirstByIdDetalleFactura(Long idDetalleFactura);
    List<DetalleFacturaEntity> findByIdFactura(Long idFactura);
    List<DetalleFacturaEntity> findByIdProducto(Long idProducto);
}

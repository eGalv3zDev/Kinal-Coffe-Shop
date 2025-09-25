package org.asco_devs.kinalcoffeeshop.persistence.crud;

import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudPedidoEntity extends CrudRepository<PedidoEntity, Long> {

//    List<PedidoEntity> findByIdAlumno(Long idAlumno);
//    List<PedidoEntity> findByIdUsuarioCredito(Long idUsuarioCredito);
    List<PedidoEntity> findByIdAlumno_Nombre(String nombre);
}
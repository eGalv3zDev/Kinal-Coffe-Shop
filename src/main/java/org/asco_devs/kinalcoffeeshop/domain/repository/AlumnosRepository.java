package org.asco_devs.kinalcoffeeshop.domain.repository;

import org.asco_devs.kinalcoffeeshop.domain.dto.AlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModAlumnosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;

import java.util.List;
import java.util.Optional;

public interface AlumnosRepository {
    List<AlumnosDto> obtenerTodos();
    Optional<AlumnosDto> buscarPorId(Long idAlumno);
    AlumnosDto guardar(AlumnosDto alumnosDto);
    Optional<AlumnosDto> modificar(Long idAlumno, ModAlumnosDto modAlumnosDto);
    void eliminar(Long idAlumno);
    Optional<Alumnos> buscarPorCorreo(String correo);
}
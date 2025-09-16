package org.asco_devs.kinalcoffeeshop.dominio.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.AlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModAlumnoDto;

import java.util.List;

public interface AlumnoRepository {
    List<AlumnoDto> obtenerAlumnos();
    AlumnoDto buscarPorId(Long idAlumno);
    AlumnoDto guardarAlumno(AlumnoDto dto);
    AlumnoDto modificarAlumno(Long idAlumno, ModAlumnoDto mod);
    void eliminarAlumno(Long idAlumno);
}

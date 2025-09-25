package org.asco_devs.kinalcoffeeshop.domain.service;

import org.asco_devs.kinalcoffeeshop.domain.dto.AlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModAlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.AlumnoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.AlumnosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosService {

    private final AlumnosRepository alumnosRepository;

    public AlumnosService(AlumnosRepository alumnosRepository) {
        this.alumnosRepository = alumnosRepository;
    }

    public List<AlumnosDto> obtenerTodo() {
        return alumnosRepository.obtenerTodos();
    }

    public AlumnosDto buscarPorCodigo(Long idAlumno) {
        return alumnosRepository.buscarPorId(idAlumno)
                .orElseThrow(() -> new AlumnoNotExistsException(idAlumno));
    }

    public AlumnosDto guardarAlumno(AlumnosDto alumnosDto) {
        return alumnosRepository.guardar(alumnosDto);
    }

    public AlumnosDto modificarAlumno(Long idAlumno, ModAlumnosDto modAlumnosDto) {
        return alumnosRepository.modificar(idAlumno, modAlumnosDto)
                .orElseThrow(() -> new AlumnoNotExistsException(idAlumno));
    }

    public void eliminarAlumno(Long idAlumno) {
        alumnosRepository.eliminar(idAlumno);
    }
}
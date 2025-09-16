package org.asco_devs.kinalcoffeeshop.dominio.service;

import org.asco_devs.kinalcoffeeshop.dominio.dto.AlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModAlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<AlumnoDto> obtenerAlumnos() {
        return this.alumnoRepository.obtenerAlumnos();
    }

    public AlumnoDto buscarCodigo(Long codigo){
        return this.alumnoRepository.buscarPorId(codigo);
    }

    public AlumnoDto guardarAlumno(AlumnoDto alumnoDto){
        return this.alumnoRepository.guardarAlumno(alumnoDto);
    }

    public AlumnoDto modificarAlumno(Long codigo, ModAlumnoDto mod){
        return this.alumnoRepository.modificarAlumno(codigo, mod);
    }

    public void eliminarAlumno(Long codigo){
        this.alumnoRepository.eliminarAlumno(codigo);
    }
}

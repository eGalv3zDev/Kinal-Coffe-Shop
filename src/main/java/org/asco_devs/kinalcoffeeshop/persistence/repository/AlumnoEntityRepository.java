package org.asco_devs.kinalcoffeeshop.persistence.repository;

import org.asco_devs.kinalcoffeeshop.dominio.dto.alumno.AlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.alumno.ModAlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.alumno.AlumnoAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.alumno.AlumnoNotExistException;
import org.asco_devs.kinalcoffeeshop.dominio.repository.AlumnoRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudAlumnoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.AlumnoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.AlumnoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlumnoEntityRepository implements AlumnoRepository {
    private final CrudAlumnoEntity crudAlumnoEntity;
    private final AlumnoMapper alumnoMapper;

    public AlumnoEntityRepository(CrudAlumnoEntity crudAlumnoEntity, AlumnoMapper alumnoMapper) {
        this.crudAlumnoEntity = crudAlumnoEntity;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public List<AlumnoDto> obtenerAlumnos() {
        return this.alumnoMapper.toDto(this.crudAlumnoEntity.findAll());
    }

    @Override
    public AlumnoDto buscarPorId(Long idAlumno) {
        return this.alumnoMapper.toDto(this.crudAlumnoEntity.findById(idAlumno).orElse(null));
    }

    @Override
    public AlumnoDto buscarPorCarnet(String carnet) {
        return this.alumnoMapper.toDto(this.crudAlumnoEntity.findFirstByCarnet(carnet));
    }

    @Override
    public AlumnoDto guardarAlumno(AlumnoDto dto) {
        if(this.crudAlumnoEntity.findFirstByNombre(dto.name()) != null) {
            throw new AlumnoAlreadyExistException(dto.name());
        }
        AlumnoEntity alumno = this.alumnoMapper.toEntity(dto);
        this.crudAlumnoEntity.save(alumno);
        return this.alumnoMapper.toDto(alumno);
    }

    @Override
    public AlumnoDto modificarAlumno(Long idAlumno, ModAlumnoDto mod) {
        AlumnoEntity alumnoEntity = this.crudAlumnoEntity.findById(idAlumno).orElse(null);
        if(alumnoEntity == null) {
            throw new AlumnoNotExistException(idAlumno);
        }
        this.alumnoMapper.modificarEntityFromDto(mod, alumnoEntity);
        return this.alumnoMapper.toDto(this.crudAlumnoEntity.save(alumnoEntity));
    }

    @Override
    public void eliminarAlumno(Long idAlumno) {
        AlumnoEntity alumnoEntity = this.crudAlumnoEntity.findById(idAlumno).orElse(null);
        if (alumnoEntity == null) {
            throw new AlumnoNotExistException(idAlumno);
        } else {
            this.crudAlumnoEntity.deleteById(idAlumno);
        }
    }
}

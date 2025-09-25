package org.asco_devs.kinalcoffeeshop.persistence;

import org.asco_devs.kinalcoffeeshop.domain.dto.AlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModAlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.exception.AlumnoExistsException;
import org.asco_devs.kinalcoffeeshop.domain.exception.AlumnoNotExistsException;
import org.asco_devs.kinalcoffeeshop.domain.repository.AlumnosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.crud.CrudAlumnosEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.AlumnosMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlumnosEntityRepository implements AlumnosRepository {

    private final CrudAlumnosEntity crudAlumnosEntity;
    private final AlumnosMapper alumnosMapper;

    public AlumnosEntityRepository(CrudAlumnosEntity crudAlumnosEntity, AlumnosMapper alumnosMapper) {
        this.crudAlumnosEntity = crudAlumnosEntity;
        this.alumnosMapper = alumnosMapper;
    }

    @Override
    public List<AlumnosDto> obtenerTodos() {
        return alumnosMapper.toDto((List<Alumnos>) crudAlumnosEntity.findAll());
    }

    @Override
    public Optional<AlumnosDto> buscarPorId(Long idAlumno) {
        return crudAlumnosEntity.findById(idAlumno)
                .map(alumnosMapper::toDto);
    }

    @Override
    public AlumnosDto guardar(AlumnosDto alumnosDto) {
        crudAlumnosEntity.findByCarnet(alumnosDto.carnet())
                .ifPresent(a -> {
                    throw new AlumnoExistsException(a.getCarnet());
                });
        Alumnos alumno = alumnosMapper.toEntity(alumnosDto);
        return alumnosMapper.toDto(crudAlumnosEntity.save(alumno));
    }

    @Override
    public Optional<AlumnosDto> modificar(Long idAlumno, ModAlumnosDto modDto) {
        return crudAlumnosEntity.findById(idAlumno)
                .map(alumno -> {
                    alumno.setNombre(modDto.nombre());
                    alumno.setApellido(modDto.apellido());
                    alumno.setCarnet(modDto.carnet());
                    alumno.setCorreo(modDto.correo());
                    alumno.setGenero(modDto.genero());
                    alumno.setFechaNacimiento(modDto.fechaNacimiento());
                    alumno.setContrasena(modDto.contrasena());
                    return alumnosMapper.toDto(crudAlumnosEntity.save(alumno));
                });
    }

    @Override
    public void eliminar(Long idAlumno) {
        if (!crudAlumnosEntity.existsById(idAlumno)) {
            throw new AlumnoNotExistsException(idAlumno);
        }
        crudAlumnosEntity.deleteById(idAlumno);
    }
}
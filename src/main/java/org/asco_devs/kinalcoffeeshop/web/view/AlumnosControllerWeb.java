package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.AlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModAlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.model.Genero;
import org.asco_devs.kinalcoffeeshop.domain.service.AlumnosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.AlumnosMapper;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("alumnosControllerWeb")
@ViewScoped
@Getter
@Setter
public class AlumnosControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AlumnosControllerWeb.class);

    @Autowired
    private AlumnosService alumnosService;
    @Autowired
    private AlumnosMapper alumnosMapper;

    private List<Alumnos> alumnos;
    private Alumnos alumnoSeleccionado;

    @PostConstruct
    public void init() {
        cargarAlumnos();
        this.alumnoSeleccionado = new Alumnos();
    }

    private void cargarAlumnos() {
        this.alumnos = alumnosService.obtenerTodo().stream()
                .map(alumnosMapper::toEntity)
                .collect(Collectors.toList());
    }

    public void prepararNuevoAlumno() {
        this.alumnoSeleccionado = new Alumnos();
    }

    public void guardarAlumno() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.alumnoSeleccionado.getIdAlumno() == null) {
                AlumnosDto dto = alumnosMapper.toDto(this.alumnoSeleccionado);
                alumnosService.guardarAlumno(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno creado correctamente."));
            } else {
                ModAlumnosDto modDto = new ModAlumnosDto(
                        this.alumnoSeleccionado.getNombre(),
                        this.alumnoSeleccionado.getApellido(),
                        this.alumnoSeleccionado.getCarnet(),
                        this.alumnoSeleccionado.getCorreo(),
                        this.alumnoSeleccionado.getGenero(),
                        this.alumnoSeleccionado.getFechaNacimiento(),
                        this.alumnoSeleccionado.getContrasena()
                );
                alumnosService.modificarAlumno(this.alumnoSeleccionado.getIdAlumno(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno actualizado correctamente."));
            }
            cargarAlumnos();
            PrimeFaces.current().executeScript("PF('ventanaModalAlumno').hide()");
            PrimeFaces.current().ajax().update("formulario-alumnos:panel-alumnos", "formulario-alumnos:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el alumno", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el alumno."));
            PrimeFaces.current().ajax().update("formulario-alumnos:mensaje-emergente");
        }
    }

    public void eliminarAlumno() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.alumnoSeleccionado == null || this.alumnoSeleccionado.getIdAlumno() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún alumno para eliminar."));
            return;
        }

        try {
            alumnosService.eliminarAlumno(this.alumnoSeleccionado.getIdAlumno());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno eliminado correctamente."));
            cargarAlumnos();
            PrimeFaces.current().ajax().update("formulario-alumnos:panel-alumnos", "formulario-alumnos:mensaje-emergente");

        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar alumno con datos relacionados. ID: {}", this.alumnoSeleccionado.getIdAlumno(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar el alumno."));
            PrimeFaces.current().ajax().update("formulario-alumnos:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el alumno con ID: {}", this.alumnoSeleccionado.getIdAlumno(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el alumno."));
            PrimeFaces.current().ajax().update("formulario-alumnos:mensaje-emergente");
        }
    }

    public Genero[] getGeneros() {
        return Genero.values();
    }
}
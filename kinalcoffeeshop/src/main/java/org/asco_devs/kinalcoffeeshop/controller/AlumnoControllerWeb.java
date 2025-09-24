package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.alumno.AlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.alumno.ModAlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.AlumnoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Data
@Named("alumnoControllerWeb")
public class AlumnoControllerWeb implements Serializable {

    @Autowired
    AlumnoService alumnoService;

    private List<AlumnoDto> alumnos;
    private AlumnoDto alumnoSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(AlumnoControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarAlumnos();
    }

    public void cargarAlumnos() {
        this.alumnos = this.alumnoService.obtenerAlumnos();
        this.alumnos.forEach(alumno -> logger.info("Alumno cargado: " + alumno.toString()));
    }

    public void agregarAlumno() {
        this.alumnoSeleccionado = new AlumnoDto(null, null, null, null, null, null, null, null);
    }

    public void guardarAlumno() {
        logger.info("Alumno a guardar: " + this.alumnoSeleccionado);

        if (this.alumnoSeleccionado.id() == null) {
            // Guardar un nuevo alumno
            this.alumnoService.guardarAlumno(this.alumnoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno agregado exitosamente."));
        } else {
            // Modificar un alumno existente
            ModAlumnoDto modDto = new ModAlumnoDto(
                    this.alumnoSeleccionado.name(),
                    this.alumnoSeleccionado.lastName(),
                    this.alumnoSeleccionado.carnet(),
                    this.alumnoSeleccionado.email(),
                    this.alumnoSeleccionado.genre(),
                    this.alumnoSeleccionado.birthDate(),
                    this.alumnoSeleccionado.password()
            );
            this.alumnoService.modificarAlumno(this.alumnoSeleccionado.id(), modDto);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno actualizado exitosamente."));
        }

        cargarAlumnos();

        PrimeFaces.current().executeScript("PF('ventanaModalAlumno').hide()");
        PrimeFaces.current().ajax().update("formulario-alumnos:mensaje-emergente",
                "formulario-alumnos:tabla-alumnos");
        this.alumnoSeleccionado = null;
    }

    public void eliminarAlumno() {
        logger.info("Alumno a eliminar: " + this.alumnoSeleccionado);
        this.alumnoService.eliminarAlumno(this.alumnoSeleccionado.id());
        this.alumnoSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno eliminado exitosamente."));

        cargarAlumnos();

        PrimeFaces.current().ajax().update("formulario-alumnos:mensaje-emergente",
                "formulario-alumnos:tabla-alumnos");
    }
}

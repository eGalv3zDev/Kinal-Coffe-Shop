package org.asco_devs.kinalcoffeeshop.web;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.dominio.dto.alumno.AlumnoDtoWeb;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Named
@ViewScoped
@Getter
@Setter
public class AlumnoControllerWeb implements Serializable {

    private List<AlumnoDtoWeb> alumnos;
    private AlumnoDtoWeb alumnoSeleccionado;

    @PostConstruct
    public void init() {
        alumnos = new ArrayList<>();
        // Datos de ejemplo para la inicialización
        alumnos.add(new AlumnoDtoWeb(1L, "Juan", "Pérez", "2021001", "juan.perez@example.com", "Masculino", LocalDate.of(2003, 5, 15), "password123"));
        alumnos.add(new AlumnoDtoWeb(2L, "María", "García", "2021002", "maria.garcia@example.com", "Femenino", LocalDate.of(2002, 8, 22), "password456"));
        alumnos.add(new AlumnoDtoWeb(3L, "Carlos", "López", "2021003", "carlos.lopez@example.com", "Masculino", LocalDate.of(2004, 1, 30), "password789"));
        alumnos.add(new AlumnoDtoWeb(4L, "Ana", "Martínez", "2021004", "ana.martinez@example.com", "Femenino", LocalDate.of(2003, 11, 10), "password101"));

        // Inicializar el objeto para un nuevo alumno
        prepararNuevoAlumno();
    }

    /**
     * Prepara la instancia de alumnoSeleccionado para un nuevo registro.
     */
    public void prepararNuevoAlumno() {
        alumnoSeleccionado = new AlumnoDtoWeb();
    }

    /**
     * Guarda un alumno nuevo o actualiza uno existente.
     */
    public void guardarAlumno() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (alumnoSeleccionado.getId() == null) {
            // Es un nuevo alumno
            alumnoSeleccionado.setId(generarIdUnico()); // Asigna un nuevo ID
            alumnos.add(alumnoSeleccionado);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno creado correctamente."));
        } else {
            // Es una actualización
            int index = -1;
            for (int i = 0; i < alumnos.size(); i++) {
                if (Objects.equals(alumnos.get(i).getId(), alumnoSeleccionado.getId())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                alumnos.set(index, alumnoSeleccionado);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno actualizado correctamente."));
            }
        }
        // Prepara para un posible nuevo alumno después de guardar
        prepararNuevoAlumno();
    }

    public void eliminarAlumno() {
        alumnos.remove(alumnoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Alumno eliminado correctamente."));
        // Limpia la selección
        prepararNuevoAlumno();
    }

    /**
     * Genera un ID único para un nuevo alumno.
     * En una aplicación real, esto sería manejado por la base de datos.
     * @return Un nuevo ID de tipo Long.
     */
    private Long generarIdUnico() {
        return ThreadLocalRandom.current().nextLong(100, 1000);
    }
}
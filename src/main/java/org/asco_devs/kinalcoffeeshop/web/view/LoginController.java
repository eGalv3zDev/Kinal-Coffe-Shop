package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.repository.AlumnosRepository;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Named("loginController")
@RequestScoped
@Getter
@Setter
public class LoginController {

    @Autowired
    private AlumnosRepository alumnosRepository;

    private String correo;
    private String contrasena;

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Alumnos alumno = alumnosRepository.buscarPorCorreo(correo)
                    .orElse(null);

            if (alumno != null && alumno.getContrasena().equals(this.contrasena)) {
                context.getExternalContext().redirect("menu.xhtml");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Acceso", "Correo o contraseña incorrectos."));
            }
        } catch (IOException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "No se pudo redirigir la página."));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Inesperado", "Ocurrió un error al intentar iniciar sesión."));
        }
    }
}
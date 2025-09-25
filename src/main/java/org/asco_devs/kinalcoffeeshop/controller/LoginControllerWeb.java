package org.asco_devs.kinalcoffeeshop.controller;

import lombok.Getter;
import lombok.Setter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.asco_devs.kinalcoffeeshop.persistence.entity.AlumnoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;

import java.io.Serializable;

@Named
@RequestScoped
@Getter
@Setter
public class LoginControllerWeb implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private String correo;
    private String contrasena;

    public String login() {
        AlumnoEntity alumno = validarLoginAlumno();
        if (alumno != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Alumno " + alumno.getNombre()));
            return "index.xhtml";
        }

        UsuarioConCreditoEntity usuarioConCredito = validarLoginUsuarioConCredito();
        if (usuarioConCredito != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Usuario " + usuarioConCredito.getNombre()));
            return "index.xhtml";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Correo o contraseña incorrectos"));
        return null;
    }

    private AlumnoEntity validarLoginAlumno() {
        String query = "SELECT a FROM Alumno a WHERE a.correo = :correo AND a.contrasena = :contrasena";
        TypedQuery<AlumnoEntity> typedQuery = entityManager.createQuery(query, AlumnoEntity.class);
        typedQuery.setParameter("correo", correo);
        typedQuery.setParameter("contraseña", contrasena);

        return typedQuery.getResultList().stream().findFirst().orElse(null);
    }

    private UsuarioConCreditoEntity validarLoginUsuarioConCredito() {
        String query = "SELECT u FROM UsuarioConCredito u WHERE u.correo = :correo AND u.contrasena = :contrasena";
        TypedQuery<UsuarioConCreditoEntity> typedQuery = entityManager.createQuery(query, UsuarioConCreditoEntity.class);
        typedQuery.setParameter("correo", correo);
        typedQuery.setParameter("contraseña", contrasena);

        return typedQuery.getResultList().stream().findFirst().orElse(null);
    }
}


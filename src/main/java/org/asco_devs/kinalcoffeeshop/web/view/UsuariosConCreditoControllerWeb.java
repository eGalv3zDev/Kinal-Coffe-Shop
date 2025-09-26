package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModUsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.UsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.model.Genero;
import org.asco_devs.kinalcoffeeshop.domain.service.UsuariosConCreditoService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.UsuariosConCreditoMapper;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("usuariosConCreditoControllerWeb")
@ViewScoped
@Getter
@Setter
public class UsuariosConCreditoControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuariosConCreditoControllerWeb.class);

    @Autowired
    private UsuariosConCreditoService usuariosConCreditoService;
    @Autowired
    private UsuariosConCreditoMapper usuariosConCreditoMapper;

    private List<UsuariosConCredito> usuarios;
    private UsuariosConCredito usuarioSeleccionado;

    @PostConstruct
    public void init() {
        cargarUsuarios();
        this.usuarioSeleccionado = new UsuariosConCredito();
    }

    private void cargarUsuarios() {
        this.usuarios = usuariosConCreditoService.obtenerTodo().stream()
                .map(usuariosConCreditoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public void prepararNuevoUsuario() {
        this.usuarioSeleccionado = new UsuariosConCredito();
    }

    public void guardarUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.usuarioSeleccionado.getIdUsuarioCredito() == null) {
                UsuariosConCreditoDto dto = usuariosConCreditoMapper.toDto(this.usuarioSeleccionado);
                usuariosConCreditoService.guardarUsuario(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario creado correctamente."));
            } else {
                ModUsuariosConCreditoDto modDto = new ModUsuariosConCreditoDto(
                        this.usuarioSeleccionado.getNombre(),
                        this.usuarioSeleccionado.getApellido(),
                        this.usuarioSeleccionado.getTelefono(),
                        this.usuarioSeleccionado.getCorreo(),
                        this.usuarioSeleccionado.getGenero(),
                        this.usuarioSeleccionado.getFechaNacimiento(),
                        this.usuarioSeleccionado.getContrasena()
                );
                usuariosConCreditoService.modificarUsuario(this.usuarioSeleccionado.getIdUsuarioCredito(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado correctamente."));
            }
            cargarUsuarios();
            PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
            PrimeFaces.current().ajax().update("formulario-usuarios:panel-usuarios", "formulario-usuarios:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el usuario", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el usuario."));
            PrimeFaces.current().ajax().update("formulario-usuarios:mensaje-emergente");
        }
    }

    public void eliminarUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.getIdUsuarioCredito() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún usuario para eliminar."));
            return;
        }

        try {
            usuariosConCreditoService.eliminarUsuario(this.usuarioSeleccionado.getIdUsuarioCredito());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario eliminado correctamente."));
            cargarUsuarios();
            PrimeFaces.current().ajax().update("formulario-usuarios:panel-usuarios", "formulario-usuarios:mensaje-emergente");

        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar usuario con datos relacionados. ID: {}", this.usuarioSeleccionado.getIdUsuarioCredito(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar el usuario."));
            PrimeFaces.current().ajax().update("formulario-usuarios:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el usuario con ID: {}", this.usuarioSeleccionado.getIdUsuarioCredito(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el usuario."));
            PrimeFaces.current().ajax().update("formulario-usuarios:mensaje-emergente");
        }
    }

    public Genero[] getGeneros() {
        return Genero.values();
    }
}
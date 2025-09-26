package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.CuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.service.CuentasDeCreditoService;
import org.asco_devs.kinalcoffeeshop.domain.service.UsuariosConCreditoService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentasDeCredito;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CuentasDeCreditoMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.UsuariosConCreditoMapper;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named("cuentasDeCreditoControllerWeb")
@ViewScoped
@Getter
@Setter
public class CuentasDeCreditoControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CuentasDeCreditoControllerWeb.class);

    @Autowired private CuentasDeCreditoService cuentasDeCreditoService;
    @Autowired private UsuariosConCreditoService usuariosConCreditoService;
    @Autowired private CuentasDeCreditoMapper cuentasDeCreditoMapper;
    @Autowired private UsuariosConCreditoMapper usuariosConCreditoMapper;

    private List<CuentasDeCredito> cuentas;
    private CuentasDeCredito cuentaSeleccionada;
    private List<UsuariosConCredito> listaUsuarios;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarCuentas();
        this.cuentaSeleccionada = new CuentasDeCredito();
    }

    private void cargarListasDesplegables() {
        this.listaUsuarios = usuariosConCreditoService.obtenerTodo().stream()
                .map(usuariosConCreditoMapper::toEntity)
                .collect(Collectors.toList());
    }

    private void cargarCuentas() {
        Map<Long, UsuariosConCredito> usuariosMap = listaUsuarios.stream()
                .collect(Collectors.toMap(UsuariosConCredito::getIdUsuarioCredito, Function.identity()));

        this.cuentas = cuentasDeCreditoService.obtenerTodo().stream()
                .map(cuentasDeCreditoMapper::toEntity)
                .peek(cuenta -> cuenta.setUsuarioConCredito(usuariosMap.get(cuenta.getIdUsuarioCredito())))
                .collect(Collectors.toList());
    }

    public void prepararNuevaCuenta() {
        this.cuentaSeleccionada = new CuentasDeCredito();
    }

    public void guardarCuenta() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.cuentaSeleccionada.setIdUsuarioCredito(this.cuentaSeleccionada.getUsuarioConCredito().getIdUsuarioCredito());

            if (this.cuentaSeleccionada.getIdCuenta() == null) {
                CuentasDeCreditoDto dto = cuentasDeCreditoMapper.toDto(this.cuentaSeleccionada);
                cuentasDeCreditoService.guardarCuenta(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cuenta creada correctamente."));
            } else {
                ModCuentasDeCreditoDto modDto = new ModCuentasDeCreditoDto(
                        this.cuentaSeleccionada.getSaldo(),
                        this.cuentaSeleccionada.getIdUsuarioCredito()
                );
                cuentasDeCreditoService.modificarCuenta(this.cuentaSeleccionada.getIdCuenta(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cuenta actualizada correctamente."));
            }
            cargarCuentas();
            PrimeFaces.current().executeScript("PF('ventanaModalCuenta').hide()");
            PrimeFaces.current().ajax().update("formulario-cuentas:panel-cuentas", "formulario-cuentas:mensaje-emergente");

        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de crear una cuenta para un usuario que ya tiene una. Usuario ID: {}", this.cuentaSeleccionada.getIdUsuarioCredito(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Duplicado", "El usuario seleccionado ya posee una cuenta de crédito."));
            PrimeFaces.current().ajax().update("formulario-cuentas:mensaje-emergente");
        } catch (Exception e) {
            log.error("Ocurrió un error al guardar la cuenta", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar la cuenta."));
            PrimeFaces.current().ajax().update("formulario-cuentas:mensaje-emergente");
        }
    }

    public void eliminarCuenta() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.cuentaSeleccionada == null || this.cuentaSeleccionada.getIdCuenta() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ninguna cuenta."));
            return;
        }

        try {
            cuentasDeCreditoService.eliminarCuenta(this.cuentaSeleccionada.getIdCuenta());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cuenta eliminada correctamente."));
            cargarCuentas();
            PrimeFaces.current().ajax().update("formulario-cuentas:panel-cuentas", "formulario-cuentas:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar la cuenta con ID: {}", this.cuentaSeleccionada.getIdCuenta(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la cuenta."));
            PrimeFaces.current().ajax().update("formulario-cuentas:mensaje-emergente");
        }
    }
}
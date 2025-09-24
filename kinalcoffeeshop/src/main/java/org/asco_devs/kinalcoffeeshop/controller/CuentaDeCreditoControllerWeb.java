package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito.CuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.cuentaDeCredito.ModCuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.CuentaDeCreditoService;
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
@Named("cuentaDeCreditoControllerWeb")
public class CuentaDeCreditoControllerWeb implements Serializable {

    @Autowired
    CuentaDeCreditoService cuentaDeCreditoService;

    private List<CuentaDeCreditoDto> cuentas;
    private CuentaDeCreditoDto cuentaSeleccionada;
    private static final Logger logger = LoggerFactory.getLogger(CuentaDeCreditoControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarCuentas();
    }

    public void cargarCuentas() {
        this.cuentas = this.cuentaDeCreditoService.obtenerCuentasDeCredito();
        this.cuentas.forEach(cuenta -> logger.info("Cuenta de crédito cargada: " + cuenta.toString()));
    }

    public void agregarCuenta() {
        this.cuentaSeleccionada = new CuentaDeCreditoDto(null, null, null);
    }

    public void guardarCuenta() {
        logger.info("Cuenta de crédito a guardar: " + this.cuentaSeleccionada);
        if (this.cuentaSeleccionada.id() == null) {
            this.cuentaDeCreditoService.guardarCuentaDeCredito(this.cuentaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cuenta de crédito agregada exitosamente."));
        } else {
            // Se asume que existe un método de modificación en el servicio.
            ModCuentaDeCreditoDto modDto = new ModCuentaDeCreditoDto(this.cuentaSeleccionada.credit(), this.cuentaSeleccionada.creditUserId());
            this.cuentaDeCreditoService.modificarCuentaDeCredito(this.cuentaSeleccionada.id(), modDto);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cuenta de crédito actualizada exitosamente."));
        }

        cargarCuentas();

        PrimeFaces.current().executeScript("PF('ventanaModalCuenta').hide()");
        PrimeFaces.current().ajax().update("formulario-cuentas:mensaje-emergente",
                "formulario-cuentas:tabla-cuentas");
        this.cuentaSeleccionada = null;
    }

    public void eliminarCuenta() {
        logger.info("Cuenta de crédito a eliminar: " + this.cuentaSeleccionada);
        this.cuentaDeCreditoService.eliminarCuentaDeCredito(this.cuentaSeleccionada.id());
        this.cuentaSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cuenta de crédito eliminada exitosamente."));

        cargarCuentas();

        PrimeFaces.current().ajax().update("formulario-cuentas:mensaje-emergente",
                "formulario-cuentas:tabla-cuentas");
    }
}

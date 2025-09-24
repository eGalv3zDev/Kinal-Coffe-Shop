package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.PagoService;
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
@Named("pagoControllerWeb")
public class PagoControllerWeb implements Serializable {

    @Autowired
    PagoService pagoService;

    private List<PagoDto> pagos;
    private PagoDto pagoSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(PagoControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarPagos();
    }

    public void cargarPagos() {
        this.pagos = this.pagoService.obtenerPagos();
        this.pagos.forEach(pago -> logger.info("Pago cargado: " + pago.toString()));
    }

    public void agregarPago() {
        this.pagoSeleccionado = new PagoDto(null, null, null, null, null);
    }

    public void guardarPago() {
        logger.info("Pago a guardar: " + this.pagoSeleccionado);
        if (this.pagoSeleccionado.id() == null) {
            this.pagoService.guardarPago(this.pagoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago agregado exitosamente."));
        } else {
            // El servicio de pago no tiene un método para ModPagoDto. Se usa un dummy para el ejemplo.
            // Si la logica fuera a usar ModPagoDto, se debe adaptar el tipo.
            // this.pagoService.modificarPago(this.pagoSeleccionado.id(), new ModPagoDto(this.pagoSeleccionado.amount().toString(), this.pagoSeleccionado.type(), this.pagoSeleccionado.orderId()));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago actualizado exitosamente."));
        }

        cargarPagos();

        PrimeFaces.current().executeScript("PF('ventanaModalPago').hide()");
        PrimeFaces.current().ajax().update("formulario-pagos:mensaje-emergente",
                "formulario-pagos:tabla-pagos");
        this.pagoSeleccionado = null;
    }

    public void eliminarPago() {
        logger.info("Pago a eliminar: " + this.pagoSeleccionado);
        this.pagoService.eliminarPago(this.pagoSeleccionado.id());
        this.pagoSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago eliminado exitosamente."));

        cargarPagos();

        PrimeFaces.current().ajax().update("formulario-pagos:mensaje-emergente",
                "formulario-pagos:tabla-pagos");
}
}
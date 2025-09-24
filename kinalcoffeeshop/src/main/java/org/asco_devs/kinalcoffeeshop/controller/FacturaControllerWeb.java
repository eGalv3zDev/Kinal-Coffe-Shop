package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.FacturaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Named("facturaControllerWeb")
@Component
@ViewScoped
@Data
public class FacturaControllerWeb implements Serializable {

    @Inject
    private FacturaService facturaService;

    private List<FacturaDto> facturas;
    private FacturaDto facturaSeleccionada;
    private static final Logger logger = LoggerFactory.getLogger(FacturaControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarFacturas();
    }

    public void cargarFacturas() {
        this.facturas = facturaService.obtenerFacturas();
    }

    public void agregarFactura() {
        this.facturaSeleccionada = new FacturaDto(null, null, null, null);
    }

    public void guardarFactura() {
        logger.info("Factura a guardar: " + this.facturaSeleccionada);
        try {
            if (this.facturaSeleccionada.id() == null) {
                this.facturaService.guardarFactura(this.facturaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Factura guardada correctamente."));
            } else {
                ModFacturaDto modDto = new ModFacturaDto(
                        this.facturaSeleccionada.total(),
                        this.facturaSeleccionada.orderId()
                );
                this.facturaService.modificarFactura(this.facturaSeleccionada.id(), modDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Factura actualizada correctamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        cargarFacturas();
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').hide()");
        PrimeFaces.current().ajax().update("formulario-facturas");
        this.facturaSeleccionada = null;
    }

    public void eliminarFactura() {
        logger.info("Factura a eliminar: " + this.facturaSeleccionada);
        if (this.facturaSeleccionada != null && this.facturaSeleccionada.id() != null) {
            try {
                facturaService.eliminarFactura(this.facturaSeleccionada.id());
                this.facturaSeleccionada = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Factura eliminada."));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
            cargarFacturas();
            PrimeFaces.current().ajax().update("formulario-facturas");
        }
    }
}
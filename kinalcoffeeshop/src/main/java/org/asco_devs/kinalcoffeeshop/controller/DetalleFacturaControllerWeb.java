package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detalleFactura.DetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detalleFactura.ModDetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetalleFacturaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Controller web para la gestión de detalles de facturas.
 * Utiliza PrimeFaces y JSF para interactuar con la capa de servicio.
 */
@Named("detalleFacturaControllerWeb")
@Component
@ViewScoped
@Data
public class DetalleFacturaControllerWeb implements Serializable {

    @Inject
    private DetalleFacturaService detalleFacturaService;

    private List<DetalleFacturaDto> detalles;
    private DetalleFacturaDto detalleSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(DetalleFacturaControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarDetallesFactura();
    }

    /**
     * Carga todos los detalles de factura desde el servicio.
     */
    public void cargarDetallesFactura() {
        this.detalles = detalleFacturaService.obtenerDetalleFactura();
    }

    /**
     * Prepara un nuevo DTO de detalle de factura para la creación.
     */
    public void agregarDetalleFactura() {
        this.detalleSeleccionado = new DetalleFacturaDto(null, null, null, null, null);
    }

    /**
     * Guarda o actualiza un detalle de factura.
     */
    public void guardarDetalleFactura() {
        logger.info("Detalle de factura a guardar: " + this.detalleSeleccionado);
        try {
            if (this.detalleSeleccionado.id() == null) {
                this.detalleFacturaService.guardarDetalleFactura(this.detalleSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de factura guardado correctamente."));
            } else {
                ModDetalleFacturaDto modDto = new ModDetalleFacturaDto(
                        this.detalleSeleccionado.stock(),
                        this.detalleSeleccionado.subStock(),
                        this.detalleSeleccionado.facturaId(),
                        this.detalleSeleccionado.productId()
                );
                this.detalleFacturaService.modificarDetalleFactura(this.detalleSeleccionado.id(), modDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de factura actualizado correctamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        cargarDetallesFactura();
        PrimeFaces.current().executeScript("PF('ventanaModalDetalle').hide()");
        PrimeFaces.current().ajax().update("formulario-detalles:mensaje-emergente", "formulario-detalles:tabla-detalles");
        this.detalleSeleccionado = null;
    }

    /**
     * Elimina un detalle de factura seleccionado.
     */
    public void eliminarDetalleFactura() {
        logger.info("Detalle de factura a eliminar: " + this.detalleSeleccionado);
        if (this.detalleSeleccionado != null && this.detalleSeleccionado.id() != null) {
            try {
                detalleFacturaService.eliminarDetalleFactura(this.detalleSeleccionado.id());
                this.detalleSeleccionado = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de factura eliminado."));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
            cargarDetallesFactura();
            PrimeFaces.current().ajax().update("formulario-detalles:mensaje-emergente", "formulario-detalles:tabla-detalles");
        }
    }
}

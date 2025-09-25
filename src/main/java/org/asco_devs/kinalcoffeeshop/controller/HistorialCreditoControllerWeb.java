package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito.HistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito.ModHistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.HistorialCreditoService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PagoEntity;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named("historialCreditoControllerWeb")
@Component
@ViewScoped
@Data
public class HistorialCreditoControllerWeb implements Serializable {

    @Inject
    private HistorialCreditoService historialCreditoService;

    private List<HistorialCreditoDto> historiales;
    private HistorialCreditoDto historialSeleccionado;
    private Long selectedCreditLineId;
    private Long selectedPaymentId;
    private static final Logger logger = LoggerFactory.getLogger(HistorialCreditoControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarHistoriales();
    }

    public void cargarHistoriales() {
        this.historiales = historialCreditoService.obtenerHistoriales();
    }

    public void agregarHistorial() {
        this.historialSeleccionado = new HistorialCreditoDto(null, LocalDate.now(), null, null, null, null);
        this.selectedCreditLineId = null;
        this.selectedPaymentId = null;
    }

    public void guardarHistorial() {
        logger.info("Historial a guardar: " + this.historialSeleccionado);
        try {
            if (this.historialSeleccionado.id() == null) {
                LineaDeCreditoEntity creditLine = new LineaDeCreditoEntity();
                creditLine.setIdConsumo(selectedCreditLineId);

                PagoEntity payment = new PagoEntity();
                payment.setIdPago(selectedPaymentId);

                HistorialCreditoDto nuevoDto = new HistorialCreditoDto(
                        null,
                        LocalDate.now(),
                        historialSeleccionado.movementType(),
                        historialSeleccionado.amount(),
                        creditLine,
                        payment
                );

                this.historialCreditoService.guardarHistorial(nuevoDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Historial guardado correctamente."));
            } else {
                ModHistorialCreditoDto modDto = new ModHistorialCreditoDto(
                        this.historialSeleccionado.movementType(),
                        this.historialSeleccionado.amount()
                );
                this.historialCreditoService.modificarHistorial(this.historialSeleccionado.id(), modDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Historial actualizado correctamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        cargarHistoriales();
        PrimeFaces.current().executeScript("PF('ventanaModalHistorial').hide()");
        PrimeFaces.current().ajax().update("formulario-historiales");
        this.historialSeleccionado = null;
    }

    public void eliminarHistorial() {
        logger.info("Historial a eliminar: " + this.historialSeleccionado);
        if (this.historialSeleccionado != null && this.historialSeleccionado.id() != null) {
            try {
                historialCreditoService.eliminarHistorial(this.historialSeleccionado.id());
                this.historialSeleccionado = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Historial eliminado."));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
            cargarHistoriales();
            PrimeFaces.current().ajax().update("formulario-historiales");
        }
    }
}

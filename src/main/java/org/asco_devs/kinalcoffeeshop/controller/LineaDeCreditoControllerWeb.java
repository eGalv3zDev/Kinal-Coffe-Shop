package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.ModLineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.LineaDeCreditoService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named("lineaDeCreditoControllerWeb")
@Component
@ViewScoped
@Data
public class LineaDeCreditoControllerWeb implements Serializable {

    @Inject
    private LineaDeCreditoService lineaDeCreditoService;

    private List<LineaDeCreditoDto> lineas;
    private LineaDeCreditoDto lineaSeleccionada;
    private Long selectedProductId;
    private Long selectedCreditAccountId;
    private static final Logger logger = LoggerFactory.getLogger(LineaDeCreditoControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarLineas();
    }

    public void cargarLineas() {
        this.lineas = lineaDeCreditoService.obtenerLineasDeCredito();
    }

    public void agregarLinea() {
        this.lineaSeleccionada = new LineaDeCreditoDto(null, null, null, null, null, null);
        this.selectedProductId = null;
        this.selectedCreditAccountId = null;
    }

    public void guardarLinea() {
        logger.info("Línea a guardar: " + this.lineaSeleccionada);
        try {
            if (this.lineaSeleccionada.id() == null) {
                ProductoEntity producto = new ProductoEntity();
                producto.setIdProducto(selectedProductId);

                CuentaDeCreditoEntity cuenta = new CuentaDeCreditoEntity();
                cuenta.setIdCuenta(selectedCreditAccountId);

                LineaDeCreditoDto nuevoDto = new LineaDeCreditoDto(
                        null,
                        LocalDateTime.now(),
                        lineaSeleccionada.stock(),
                        lineaSeleccionada.subTotal(),
                        producto,
                        cuenta
                );
                this.lineaDeCreditoService.guardarLineaDeCredito(nuevoDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Línea de crédito guardada correctamente."));
            } else {
                ModLineaDeCreditoDto modDto = new ModLineaDeCreditoDto(
                        null, // The date is not updated via this DTO
                        this.lineaSeleccionada.stock(),
                        this.lineaSeleccionada.subTotal()
                );
                this.lineaDeCreditoService.modificarLineaDeCredito(this.lineaSeleccionada.id(), modDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Línea de crédito actualizada correctamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        cargarLineas();
        PrimeFaces.current().executeScript("PF('ventanaModalLinea').hide()");
        PrimeFaces.current().ajax().update("formulario-lineas");
        this.lineaSeleccionada = null;
    }

    public void eliminarLinea() {
        logger.info("Línea a eliminar: " + this.lineaSeleccionada);
        if (this.lineaSeleccionada != null && this.lineaSeleccionada.id() != null) {
            try {
                lineaDeCreditoService.eliminarLineaDeCredito(this.lineaSeleccionada.id());
                this.lineaSeleccionada = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Línea de crédito eliminada."));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
            cargarLineas();
            PrimeFaces.current().ajax().update("formulario-lineas");
        }
    }
}

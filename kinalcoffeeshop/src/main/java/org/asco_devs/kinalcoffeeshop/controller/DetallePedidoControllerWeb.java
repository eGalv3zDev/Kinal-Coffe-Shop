package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetallePedidoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.List;

@Named("detallePedidoControllerWeb")
@Component
@ViewScoped
@Data
public class DetallePedidoControllerWeb implements Serializable {

    @Inject
    private DetallePedidoService detallePedidoService;

    private List<DetallePedidoDto> detallesPedidos;
    private DetallePedidoDto detallePedidoSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(DetallePedidoControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarDetallesPedidos();
    }

    /**
     * Carga todos los detalles de pedidos desde el servicio.
     */
    public void cargarDetallesPedidos() {
        this.detallesPedidos = detallePedidoService.obtenerDetallePedidos();
    }

    /**
     * Prepara un nuevo DTO de detalle de pedido para la creación.
     */
    public void agregarDetallePedido() {
        this.detallePedidoSeleccionado = new DetallePedidoDto(null, null, null, null, null, null);
    }

    /**
     * Guarda o actualiza un detalle de pedido.
     */
    public void guardarDetallePedido() {
        logger.info("Detalle de pedido a guardar: " + this.detallePedidoSeleccionado);
        try {
            if (this.detallePedidoSeleccionado.id() == null) {
                this.detallePedidoService.guardarDetalle(this.detallePedidoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido guardado correctamente."));
            } else {
                ModDetallePedidoDto modDto = new ModDetallePedidoDto(
                        this.detallePedidoSeleccionado.stock(),
                        this.detallePedidoSeleccionado.subTotal()
                );
                this.detallePedidoService.modificarDetalle(this.detallePedidoSeleccionado.id(), modDto);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido actualizado correctamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        cargarDetallesPedidos();
        PrimeFaces.current().executeScript("PF('ventanaModalDetallePedido').hide()");
        PrimeFaces.current().ajax().update("formulario-detalles-pedido");
        this.detallePedidoSeleccionado = null;
    }

    /**
     * Elimina un detalle de pedido seleccionado.
     */
    public void eliminarDetallePedido() {
        logger.info("Detalle de pedido a eliminar: " + this.detallePedidoSeleccionado);
        if (this.detallePedidoSeleccionado != null && this.detallePedidoSeleccionado.id() != null) {
            try {
                detallePedidoService.eliminarDetalle(this.detallePedidoSeleccionado.id());
                this.detallePedidoSeleccionado = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido eliminado."));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
            cargarDetallesPedidos();
            PrimeFaces.current().ajax().update("formulario-detalles-pedido");
        }
    }
}
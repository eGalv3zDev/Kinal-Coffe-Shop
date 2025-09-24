package org.asco_devs.kinalcoffeeshop.controller;

import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDtoWeb;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@SessionScope
public class DetallePedidoControllerWeb implements Serializable {

    @Autowired
    private DetallePedidoService detallePedidoService;

    private List<DetallePedidoDtoWeb> detallesPedidos;
    private DetallePedidoDtoWeb detallePedidoSeleccionado;

    @PostConstruct
    public void init() {
        cargarDetallesPedidos();
    }

    public void cargarDetallesPedidos() {
        this.detallesPedidos = detallePedidoService.obtenerDetallePedidos().stream()
                .map(this::toDetallePedidoDtoWeb)
                .collect(Collectors.toList());
    }

    public void agregarDetallePedido() {
        // Siempre crea una nueva instancia para evitar NPE
        this.detallePedidoSeleccionado = new DetallePedidoDtoWeb();
    }

    public void guardarDetallePedido() {
        try {
            if (detallePedidoSeleccionado != null) {
                DetallePedidoDto detalleDto = toDetallePedidoDto(detallePedidoSeleccionado);
                detallePedidoService.guardarDetalle(detalleDto);
                cargarDetallesPedidos();
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido guardado correctamente.");
            } else {
                agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "El objeto a guardar es nulo.");
            }
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el detalle de pedido: " + e.getMessage());
        }
    }

    public void eliminarDetallePedido() {
        try {
            if (detallePedidoSeleccionado != null) {
                detallePedidoService.eliminarDetalle(detallePedidoSeleccionado.getId());
                cargarDetallesPedidos();
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido eliminado correctamente.");
            }
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el detalle de pedido: " + e.getMessage());
        }
    }

    private DetallePedidoDtoWeb toDetallePedidoDtoWeb(DetallePedidoDto dto) {
        return new DetallePedidoDtoWeb(
                dto.id(),
                dto.productName(),
                dto.stock(),
                dto.subTotal(),
                dto.orderId(),
                dto.productId()
        );
    }

    private DetallePedidoDto toDetallePedidoDto(DetallePedidoDtoWeb dtoWeb) {
        return new DetallePedidoDto(
                dtoWeb.getId(),
                dtoWeb.getProductName(),
                dtoWeb.getStock(),
                dtoWeb.getSubTotal(),
                dtoWeb.getOrderId(),
                dtoWeb.getProductId()
        );
    }

    private void agregarMensaje(FacesMessage.Severity severidad, String resumen, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, resumen, detalle));
    }

    // --- Getters y Setters ---

    public List<DetallePedidoDtoWeb> getDetallesPedidos() {
        return detallesPedidos;
    }

    public DetallePedidoDtoWeb getDetallePedidoSeleccionado() {
        // Add a null check here to prevent the initial error
        if (detallePedidoSeleccionado == null) {
            detallePedidoSeleccionado = new DetallePedidoDtoWeb();
        }
        return detallePedidoSeleccionado;
    }

    public void setDetallePedidoSeleccionado(DetallePedidoDtoWeb detallePedidoSeleccionado) {
        this.detallePedidoSeleccionado = detallePedidoSeleccionado;
    }
}
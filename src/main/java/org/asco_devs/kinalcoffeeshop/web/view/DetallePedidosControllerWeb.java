package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.DetallePedidosService;
import org.asco_devs.kinalcoffeeshop.domain.service.PedidosService;
import org.asco_devs.kinalcoffeeshop.domain.service.ProductosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetallePedidosMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PedidosMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.ProductosMapper;
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

@Named("detallePedidosControllerWeb")
@ViewScoped
@Getter
@Setter
public class DetallePedidosControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetallePedidosControllerWeb.class);

    @Autowired private DetallePedidosService detallePedidosService;
    @Autowired private PedidosService pedidosService;
    @Autowired private ProductosService productosService;
    @Autowired private DetallePedidosMapper detallePedidosMapper;
    @Autowired private PedidosMapper pedidosMapper;
    @Autowired private ProductosMapper productosMapper;

    private List<DetallePedidos> detalles;
    private DetallePedidos detalleSeleccionado;
    private List<Pedidos> listaPedidos;
    private List<Productos> listaProductos;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarDetalles();
        this.detalleSeleccionado = new DetallePedidos();
    }

    private void cargarListasDesplegables() {
        this.listaPedidos = pedidosService.obtenerTodo().stream()
                .map(pedidosMapper::toEntity)
                .collect(Collectors.toList());
        this.listaProductos = productosService.obtenerTodo().stream()
                .map(productosMapper::toEntity)
                .collect(Collectors.toList());
    }

    private void cargarDetalles() {
        Map<Long, Pedidos> pedidosMap = listaPedidos.stream()
                .collect(Collectors.toMap(Pedidos::getIdPedido, Function.identity()));
        Map<Long, Productos> productosMap = listaProductos.stream()
                .collect(Collectors.toMap(Productos::getIdProducto, Function.identity()));

        this.detalles = detallePedidosService.obtenerTodo().stream()
                .map(detallePedidosMapper::toEntity)
                .peek(detalle -> {
                    detalle.setPedido(pedidosMap.get(detalle.getIdPedido()));
                    detalle.setProducto(productosMap.get(detalle.getIdProducto()));
                })
                .collect(Collectors.toList());
    }

    public void prepararNuevoDetalle() {
        this.detalleSeleccionado = new DetallePedidos();
    }

    public void guardarDetalle() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.detalleSeleccionado.setIdPedido(this.detalleSeleccionado.getPedido().getIdPedido());
            this.detalleSeleccionado.setIdProducto(this.detalleSeleccionado.getProducto().getIdProducto());

            if (this.detalleSeleccionado.getIdDetalle() == null) {
                DetallePedidosDto dto = detallePedidosMapper.toDto(this.detalleSeleccionado);
                detallePedidosService.guardarDetalle(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido creado correctamente."));
            } else {
                ModDetallePedidosDto modDto = new ModDetallePedidosDto(
                        this.detalleSeleccionado.getCantidad(),
                        this.detalleSeleccionado.getSubtotal(),
                        this.detalleSeleccionado.getIdPedido(),
                        this.detalleSeleccionado.getIdProducto()
                );
                detallePedidosService.modificarDetalle(this.detalleSeleccionado.getIdDetalle(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido actualizado correctamente."));
            }
            cargarDetalles();
            PrimeFaces.current().executeScript("PF('ventanaModalDetalle').hide()");
            PrimeFaces.current().ajax().update("formulario-detalles:panel-detalles", "formulario-detalles:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el detalle del pedido", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el detalle."));
            PrimeFaces.current().ajax().update("formulario-detalles:mensaje-emergente");
        }
    }

    public void eliminarDetalle() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.detalleSeleccionado == null || this.detalleSeleccionado.getIdDetalle() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún detalle."));
            return;
        }

        try {
            detallePedidosService.eliminarDetalle(this.detalleSeleccionado.getIdDetalle());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de pedido eliminado correctamente."));
            cargarDetalles();
            PrimeFaces.current().ajax().update("formulario-detalles:panel-detalles", "formulario-detalles:mensaje-emergente");
        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar un detalle con datos relacionados. ID: {}", this.detalleSeleccionado.getIdDetalle(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar el detalle."));
            PrimeFaces.current().ajax().update("formulario-detalles:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el detalle con ID: {}", this.detalleSeleccionado.getIdDetalle(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el detalle."));
            PrimeFaces.current().ajax().update("formulario-detalles:mensaje-emergente");
        }
    }
}
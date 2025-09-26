package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModPagosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PagosDto;
import org.asco_devs.kinalcoffeeshop.domain.model.TipoPago;
import org.asco_devs.kinalcoffeeshop.domain.service.PagosService;
import org.asco_devs.kinalcoffeeshop.domain.service.PedidosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pagos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PagosMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PedidosMapper;
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

@Named("pagosControllerWeb")
@ViewScoped
@Getter
@Setter
public class PagosControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PagosControllerWeb.class);

    @Autowired private PagosService pagosService;
    @Autowired private PedidosService pedidosService;
    @Autowired private PagosMapper pagosMapper;
    @Autowired private PedidosMapper pedidosMapper;

    private List<Pagos> pagos;
    private Pagos pagoSeleccionado;
    private List<Pedidos> listaPedidos;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarPagos();
        this.pagoSeleccionado = new Pagos();
    }

    private void cargarListasDesplegables() {
        this.listaPedidos = pedidosService.obtenerTodo().stream()
                .map(pedidosMapper::toEntity)
                .collect(Collectors.toList());
    }

    private void cargarPagos() {
        Map<Long, Pedidos> pedidosMap = listaPedidos.stream()
                .collect(Collectors.toMap(Pedidos::getIdPedido, Function.identity()));

        this.pagos = pagosService.obtenerTodo().stream()
                .map(pagosMapper::toEntity)
                .peek(pago -> pago.setPedido(pedidosMap.get(pago.getIdPedido())))
                .collect(Collectors.toList());
    }

    public void prepararNuevoPago() {
        this.pagoSeleccionado = new Pagos();
    }

    public void guardarPago() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.pagoSeleccionado.setIdPedido(this.pagoSeleccionado.getPedido().getIdPedido());

            if (this.pagoSeleccionado.getIdPago() == null) {
                PagosDto dto = pagosMapper.toDto(this.pagoSeleccionado);
                pagosService.guardarPago(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago creado correctamente."));
            } else {
                ModPagosDto modDto = new ModPagosDto(
                        this.pagoSeleccionado.getMonto(),
                        this.pagoSeleccionado.getTipo(),
                        this.pagoSeleccionado.getIdPedido()
                );
                pagosService.modificarPago(this.pagoSeleccionado.getIdPago(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago actualizado correctamente."));
            }
            cargarPagos();
            PrimeFaces.current().executeScript("PF('ventanaModalPago').hide()");
            PrimeFaces.current().ajax().update("formulario-pagos:panel-pagos", "formulario-pagos:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el pago", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el pago."));
            PrimeFaces.current().ajax().update("formulario-pagos:mensaje-emergente");
        }
    }

    public void eliminarPago() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.pagoSeleccionado == null || this.pagoSeleccionado.getIdPago() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún pago."));
            return;
        }

        try {
            pagosService.eliminarPago(this.pagoSeleccionado.getIdPago());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago eliminado correctamente."));
            cargarPagos();
            PrimeFaces.current().ajax().update("formulario-pagos:panel-pagos", "formulario-pagos:mensaje-emergente");
        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar un pago con datos relacionados. ID: {}", this.pagoSeleccionado.getIdPago(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar el pago."));
            PrimeFaces.current().ajax().update("formulario-pagos:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el pago con ID: {}", this.pagoSeleccionado.getIdPago(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el pago."));
            PrimeFaces.current().ajax().update("formulario-pagos:mensaje-emergente");
        }
    }

    public TipoPago[] getTiposPago() {
        return TipoPago.values();
    }
}
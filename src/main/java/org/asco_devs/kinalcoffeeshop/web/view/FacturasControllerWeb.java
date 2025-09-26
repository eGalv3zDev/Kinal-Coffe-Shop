package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.FacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.service.FacturasService;
import org.asco_devs.kinalcoffeeshop.domain.service.PedidosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Facturas;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.FacturasMapper;
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

@Named("facturasControllerWeb")
@ViewScoped
@Getter
@Setter
public class FacturasControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(FacturasControllerWeb.class);

    @Autowired private FacturasService facturasService;
    @Autowired private PedidosService pedidosService;
    @Autowired private FacturasMapper facturasMapper;
    @Autowired private PedidosMapper pedidosMapper;

    private List<Facturas> facturas;
    private Facturas facturaSeleccionada;
    private List<Pedidos> listaPedidos;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarFacturas();
        this.facturaSeleccionada = new Facturas();
    }

    private void cargarListasDesplegables() {
        this.listaPedidos = pedidosService.obtenerTodo().stream()
                .map(pedidosMapper::toEntity)
                .collect(Collectors.toList());
    }

    private void cargarFacturas() {
        Map<Long, Pedidos> pedidosMap = listaPedidos.stream()
                .collect(Collectors.toMap(Pedidos::getIdPedido, Function.identity()));

        this.facturas = facturasService.obtenerTodo().stream()
                .map(facturasMapper::toEntity)
                .peek(factura -> factura.setPedido(pedidosMap.get(factura.getIdPedido())))
                .collect(Collectors.toList());
    }

    public void prepararNuevaFactura() {
        this.facturaSeleccionada = new Facturas();
    }

    public void guardarFactura() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.facturaSeleccionada.getIdFactura() != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción no permitida", "No es posible modificar una factura existente."));
                return;
            }

            this.facturaSeleccionada.setIdPedido(this.facturaSeleccionada.getPedido().getIdPedido());
            FacturasDto dto = facturasMapper.toDto(this.facturaSeleccionada);
            facturasService.guardarFactura(dto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Factura creada correctamente."));

            cargarFacturas();
            PrimeFaces.current().executeScript("PF('ventanaModalFactura').hide()");
            PrimeFaces.current().ajax().update("formulario-facturas:panel-facturas", "formulario-facturas:mensaje-emergente");

        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de crear una factura para un pedido que ya tiene una. Pedido ID: {}", this.facturaSeleccionada.getIdPedido(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Duplicado", "El pedido seleccionado ya tiene una factura asociada."));
            PrimeFaces.current().ajax().update("formulario-facturas:mensaje-emergente");
        } catch (Exception e) {
            log.error("Ocurrió un error al guardar la factura", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar la factura."));
            PrimeFaces.current().ajax().update("formulario-facturas:mensaje-emergente");
        }
    }
}
package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.DetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.service.DetalleFacturasService;
import org.asco_devs.kinalcoffeeshop.domain.service.FacturasService;
import org.asco_devs.kinalcoffeeshop.domain.service.ProductosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturas;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Facturas;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.DetalleFacturasMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.FacturasMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.ProductosMapper;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named("detalleFacturasControllerWeb")
@ViewScoped
@Getter
@Setter
public class DetalleFacturasControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetalleFacturasControllerWeb.class);

    @Autowired private DetalleFacturasService detalleFacturasService;
    @Autowired private FacturasService facturasService;
    @Autowired private ProductosService productosService;
    @Autowired private DetalleFacturasMapper detalleFacturasMapper;
    @Autowired private FacturasMapper facturasMapper;
    @Autowired private ProductosMapper productosMapper;

    private List<DetalleFacturas> detalles;
    private DetalleFacturas detalleSeleccionado;
    private List<Facturas> listaFacturas;
    private List<Productos> listaProductos;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarDetalles();
        this.detalleSeleccionado = new DetalleFacturas();
    }

    private void cargarListasDesplegables() {
        this.listaFacturas = facturasService.obtenerTodo().stream().map(facturasMapper::toEntity).collect(Collectors.toList());
        this.listaProductos = productosService.obtenerTodo().stream().map(productosMapper::toEntity).collect(Collectors.toList());
    }

    private void cargarDetalles() {
        Map<Long, Facturas> facturasMap = listaFacturas.stream().collect(Collectors.toMap(Facturas::getIdFactura, Function.identity()));
        Map<Long, Productos> productosMap = listaProductos.stream().collect(Collectors.toMap(Productos::getIdProducto, Function.identity()));

        this.detalles = detalleFacturasService.obtenerTodo().stream()
                .map(detalleFacturasMapper::toEntity)
                .peek(detalle -> {
                    detalle.setFactura(facturasMap.get(detalle.getIdFactura()));
                    detalle.setProducto(productosMap.get(detalle.getIdProducto()));
                })
                .collect(Collectors.toList());
    }

    public void prepararNuevoDetalle() {
        this.detalleSeleccionado = new DetalleFacturas();
    }

    public void guardarDetalle() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.detalleSeleccionado.getIdDetalleFactura() != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción no permitida", "No es posible modificar un detalle de factura existente."));
                return;
            }

            this.detalleSeleccionado.setIdFactura(this.detalleSeleccionado.getFactura().getIdFactura());
            this.detalleSeleccionado.setIdProducto(this.detalleSeleccionado.getProducto().getIdProducto());

            DetalleFacturasDto dto = detalleFacturasMapper.toDto(this.detalleSeleccionado);
            detalleFacturasService.guardarDetalle(dto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Detalle de factura creado correctamente."));

            cargarDetalles();
            PrimeFaces.current().executeScript("PF('ventanaModalDetalle').hide()");
            PrimeFaces.current().ajax().update("formulario-detalles:panel-detalles", "formulario-detalles:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el detalle de factura", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el detalle."));
            PrimeFaces.current().ajax().update("formulario-detalles:mensaje-emergente");
        }
    }
}
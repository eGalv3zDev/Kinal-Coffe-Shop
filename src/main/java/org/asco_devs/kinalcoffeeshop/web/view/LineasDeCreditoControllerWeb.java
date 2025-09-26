package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.LineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModLineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.service.CuentasDeCreditoService;
import org.asco_devs.kinalcoffeeshop.domain.service.LineasDeCreditoService;
import org.asco_devs.kinalcoffeeshop.domain.service.ProductosService;
import org.asco_devs.kinalcoffeeshop.domain.service.UsuariosConCreditoService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentasDeCredito;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineasDeCredito;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CuentasDeCreditoMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.LineasDeCreditoMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.ProductosMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.UsuariosConCreditoMapper;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named("lineasDeCreditoControllerWeb")
@ViewScoped
@Getter
@Setter
public class LineasDeCreditoControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LineasDeCreditoControllerWeb.class);

    @Autowired private LineasDeCreditoService lineasDeCreditoService;
    @Autowired private ProductosService productosService;
    @Autowired private CuentasDeCreditoService cuentasDeCreditoService;
    @Autowired private UsuariosConCreditoService usuariosConCreditoService;
    @Autowired private LineasDeCreditoMapper lineasDeCreditoMapper;
    @Autowired private ProductosMapper productosMapper;
    @Autowired private CuentasDeCreditoMapper cuentasDeCreditoMapper;
    @Autowired private UsuariosConCreditoMapper usuariosConCreditoMapper;

    private List<LineasDeCredito> lineas;
    private LineasDeCredito lineaSeleccionada;
    private List<Productos> listaProductos;
    private List<CuentasDeCredito> listaCuentas;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarLineas();
        this.lineaSeleccionada = new LineasDeCredito();
    }

    private void cargarListasDesplegables() {
        this.listaProductos = productosService.obtenerTodo().stream()
                .map(productosMapper::toEntity)
                .collect(Collectors.toList());

        List<UsuariosConCredito> listaUsuarios = usuariosConCreditoService.obtenerTodo().stream()
                .map(usuariosConCreditoMapper::toEntity)
                .collect(Collectors.toList());
        Map<Long, UsuariosConCredito> usuariosMap = listaUsuarios.stream()
                .collect(Collectors.toMap(UsuariosConCredito::getIdUsuarioCredito, Function.identity()));

        this.listaCuentas = cuentasDeCreditoService.obtenerTodo().stream()
                .map(cuentasDeCreditoMapper::toEntity)
                .peek(cuenta -> cuenta.setUsuarioConCredito(usuariosMap.get(cuenta.getIdUsuarioCredito())))
                .collect(Collectors.toList());
    }

    private void cargarLineas() {
        Map<Long, Productos> productosMap = listaProductos.stream()
                .collect(Collectors.toMap(Productos::getIdProducto, Function.identity()));
        Map<Long, CuentasDeCredito> cuentasMap = listaCuentas.stream()
                .collect(Collectors.toMap(CuentasDeCredito::getIdCuenta, Function.identity()));

        this.lineas = lineasDeCreditoService.obtenerTodo().stream()
                .map(lineasDeCreditoMapper::toEntity)
                .peek(linea -> {
                    linea.setProducto(productosMap.get(linea.getIdProducto()));
                    linea.setCuenta(cuentasMap.get(linea.getIdCuenta()));
                })
                .collect(Collectors.toList());
    }

    public void prepararNuevaLinea() {
        this.lineaSeleccionada = new LineasDeCredito();
    }

    public void guardarLinea() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.lineaSeleccionada.setIdProducto(this.lineaSeleccionada.getProducto().getIdProducto());
            this.lineaSeleccionada.setIdCuenta(this.lineaSeleccionada.getCuenta().getIdCuenta());

            if (this.lineaSeleccionada.getIdConsumo() == null) {
                LineasDeCreditoDto dto = lineasDeCreditoMapper.toDto(this.lineaSeleccionada);
                lineasDeCreditoService.guardarConsumo(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Línea de crédito creada correctamente."));
            } else {
                ModLineasDeCreditoDto modDto = new ModLineasDeCreditoDto(
                        this.lineaSeleccionada.getCantidad(),
                        this.lineaSeleccionada.getSubtotal(),
                        this.lineaSeleccionada.getIdProducto(),
                        this.lineaSeleccionada.getIdCuenta()
                );
                lineasDeCreditoService.modificarConsumo(this.lineaSeleccionada.getIdConsumo(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Línea de crédito actualizada correctamente."));
            }
            cargarLineas();
            PrimeFaces.current().executeScript("PF('ventanaModalLinea').hide()");
            PrimeFaces.current().ajax().update("formulario-lineas:panel-lineas", "formulario-lineas:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar la línea de crédito", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar la línea de crédito."));
            PrimeFaces.current().ajax().update("formulario-lineas:mensaje-emergente");
        }
    }

    public void eliminarLinea() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.lineaSeleccionada == null || this.lineaSeleccionada.getIdConsumo() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ninguna línea de crédito."));
            return;
        }

        try {
            lineasDeCreditoService.eliminarConsumo(this.lineaSeleccionada.getIdConsumo());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Línea de crédito eliminada correctamente."));
            cargarLineas();
            PrimeFaces.current().ajax().update("formulario-lineas:panel-lineas", "formulario-lineas:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar la línea de crédito con ID: {}", this.lineaSeleccionada.getIdConsumo(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la línea de crédito."));
            PrimeFaces.current().ajax().update("formulario-lineas:mensaje-emergente");
        }
    }
}
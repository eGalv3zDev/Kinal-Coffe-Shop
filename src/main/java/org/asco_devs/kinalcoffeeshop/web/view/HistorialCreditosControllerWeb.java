package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.HistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModHistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.model.TipoMovimientoCredito;
import org.asco_devs.kinalcoffeeshop.domain.service.*;
import org.asco_devs.kinalcoffeeshop.persistence.entity.*;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.*;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named("historialCreditosControllerWeb")
@ViewScoped
@Getter
@Setter
public class HistorialCreditosControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(HistorialCreditosControllerWeb.class);

    @Autowired private HistorialCreditosService historialCreditosService;
    @Autowired private CuentasDeCreditoService cuentasDeCreditoService;
    @Autowired private LineasDeCreditoService lineasDeCreditoService;
    @Autowired private PagosService pagosService;
    @Autowired private UsuariosConCreditoService usuariosConCreditoService;

    @Autowired private HistorialCreditosMapper historialCreditosMapper;
    @Autowired private CuentasDeCreditoMapper cuentasDeCreditoMapper;
    @Autowired private LineasDeCreditoMapper lineasDeCreditoMapper;
    @Autowired private PagosMapper pagosMapper;
    @Autowired private UsuariosConCreditoMapper usuariosConCreditoMapper;

    private List<HistorialCreditos> historiales;
    private HistorialCreditos historialSeleccionado;
    private List<CuentasDeCredito> listaCuentas;
    private List<LineasDeCredito> listaConsumos;
    private List<Pagos> listaPagos;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarHistorial();
        this.historialSeleccionado = new HistorialCreditos();
    }

    private void cargarListasDesplegables() {
        this.listaConsumos = lineasDeCreditoService.obtenerTodo().stream().map(lineasDeCreditoMapper::toEntity).collect(Collectors.toList());
        this.listaPagos = pagosService.obtenerTodo().stream().map(pagosMapper::toEntity).collect(Collectors.toList());

        List<UsuariosConCredito> usuarios = usuariosConCreditoService.obtenerTodo().stream().map(usuariosConCreditoMapper::toEntity).collect(Collectors.toList());
        Map<Long, UsuariosConCredito> usuariosMap = usuarios.stream().collect(Collectors.toMap(UsuariosConCredito::getIdUsuarioCredito, Function.identity()));

        this.listaCuentas = cuentasDeCreditoService.obtenerTodo().stream()
                .map(cuentasDeCreditoMapper::toEntity)
                .peek(cuenta -> cuenta.setUsuarioConCredito(usuariosMap.get(cuenta.getIdUsuarioCredito())))
                .collect(Collectors.toList());
    }

    private void cargarHistorial() {
        Map<Long, CuentasDeCredito> cuentasMap = listaCuentas.stream().collect(Collectors.toMap(CuentasDeCredito::getIdCuenta, Function.identity()));
        Map<Long, LineasDeCredito> consumosMap = listaConsumos.stream().collect(Collectors.toMap(LineasDeCredito::getIdConsumo, Function.identity()));
        Map<Long, Pagos> pagosMap = listaPagos.stream().collect(Collectors.toMap(Pagos::getIdPago, Function.identity()));

        this.historiales = historialCreditosService.obtenerTodo().stream()
                .map(historialCreditosMapper::toEntity)
                .peek(historial -> {
                    historial.setCuenta(cuentasMap.get(historial.getIdCuenta()));
                    if (historial.getIdConsumo() != null) {
                        historial.setConsumo(consumosMap.get(historial.getIdConsumo()));
                    }
                    if (historial.getIdPago() != null) {
                        historial.setPago(pagosMap.get(historial.getIdPago()));
                    }
                })
                .collect(Collectors.toList());
    }

    public void prepararNuevoRegistro() {
        this.historialSeleccionado = new HistorialCreditos();
    }

    public void guardarRegistro() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.historialSeleccionado.setIdCuenta(this.historialSeleccionado.getCuenta().getIdCuenta());
            this.historialSeleccionado.setIdConsumo(this.historialSeleccionado.getConsumo() != null ? this.historialSeleccionado.getConsumo().getIdConsumo() : null);
            this.historialSeleccionado.setIdPago(this.historialSeleccionado.getPago() != null ? this.historialSeleccionado.getPago().getIdPago() : null);

            if (this.historialSeleccionado.getIdHistorial() == null) {
                HistorialCreditosDto dto = historialCreditosMapper.toDto(this.historialSeleccionado);
                historialCreditosService.guardarRegistro(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro de historial creado."));
            } else {
                ModHistorialCreditosDto modDto = new ModHistorialCreditosDto(
                        this.historialSeleccionado.getIdCuenta(),
                        this.historialSeleccionado.getTipoMovimiento(),
                        this.historialSeleccionado.getMonto(),
                        this.historialSeleccionado.getIdConsumo(),
                        this.historialSeleccionado.getIdPago()
                );
                historialCreditosService.modificarRegistro(this.historialSeleccionado.getIdHistorial(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro de historial actualizado."));
            }
            cargarHistorial();
            PrimeFaces.current().executeScript("PF('ventanaModalHistorial').hide()");
            PrimeFaces.current().ajax().update("formulario-historial:panel-historial", "formulario-historial:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el registro de historial", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el registro."));
            PrimeFaces.current().ajax().update("formulario-historial:mensaje-emergente");
        }
    }

    public void eliminarRegistro() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.historialSeleccionado == null || this.historialSeleccionado.getIdHistorial() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún registro."));
            return;
        }

        try {
            historialCreditosService.eliminarRegistro(this.historialSeleccionado.getIdHistorial());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro de historial eliminado."));
            cargarHistorial();
            PrimeFaces.current().ajax().update("formulario-historial:panel-historial", "formulario-historial:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el registro con ID: {}", this.historialSeleccionado.getIdHistorial(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el registro."));
            PrimeFaces.current().ajax().update("formulario-historial:mensaje-emergente");
        }
    }

    public TipoMovimientoCredito[] getTiposMovimiento() {
        return TipoMovimientoCredito.values();
    }
}
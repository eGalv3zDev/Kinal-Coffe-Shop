package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModPedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.model.EstadoPedido;
import org.asco_devs.kinalcoffeeshop.domain.service.AlumnosService;
import org.asco_devs.kinalcoffeeshop.domain.service.PedidosService;
import org.asco_devs.kinalcoffeeshop.domain.service.UsuariosConCreditoService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.AlumnosMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.PedidosMapper;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.UsuariosConCreditoMapper;
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

@Named("pedidosControllerWeb")
@ViewScoped
@Getter
@Setter
public class PedidosControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PedidosControllerWeb.class);

    @Autowired private PedidosService pedidosService;
    @Autowired private AlumnosService alumnosService;
    @Autowired private UsuariosConCreditoService usuariosConCreditoService;
    @Autowired private PedidosMapper pedidosMapper;
    @Autowired private AlumnosMapper alumnosMapper;
    @Autowired private UsuariosConCreditoMapper usuariosConCreditoMapper;

    private List<Pedidos> pedidos;
    private Pedidos pedidoSeleccionado;
    private List<Alumnos> listaAlumnos;
    private List<UsuariosConCredito> listaUsuarios;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarPedidos();
        this.pedidoSeleccionado = new Pedidos();
    }

    private void cargarListasDesplegables() {
        this.listaAlumnos = alumnosService.obtenerTodo().stream()
                .map(alumnosMapper::toEntity)
                .collect(Collectors.toList());
        this.listaUsuarios = usuariosConCreditoService.obtenerTodo().stream()
                .map(usuariosConCreditoMapper::toEntity)
                .collect(Collectors.toList());
    }

    private void cargarPedidos() {
        Map<Long, Alumnos> alumnosMap = listaAlumnos.stream()
                .collect(Collectors.toMap(Alumnos::getIdAlumno, Function.identity()));
        Map<Long, UsuariosConCredito> usuariosMap = listaUsuarios.stream()
                .collect(Collectors.toMap(UsuariosConCredito::getIdUsuarioCredito, Function.identity()));

        this.pedidos = pedidosService.obtenerTodo().stream()
                .map(pedidosMapper::toEntity)
                .peek(pedido -> {
                    if (pedido.getIdAlumno() != null) {
                        pedido.setAlumno(alumnosMap.get(pedido.getIdAlumno()));
                    }
                    if (pedido.getIdUsuarioCredito() != null) {
                        pedido.setUsuarioConCredito(usuariosMap.get(pedido.getIdUsuarioCredito()));
                    }
                })
                .collect(Collectors.toList());
    }

    public void prepararNuevoPedido() {
        this.pedidoSeleccionado = new Pedidos();
    }

    public void guardarPedido() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.pedidoSeleccionado.setIdAlumno(this.pedidoSeleccionado.getAlumno() != null ? this.pedidoSeleccionado.getAlumno().getIdAlumno() : null);
            this.pedidoSeleccionado.setIdUsuarioCredito(this.pedidoSeleccionado.getUsuarioConCredito() != null ? this.pedidoSeleccionado.getUsuarioConCredito().getIdUsuarioCredito() : null);

            if (this.pedidoSeleccionado.getIdPedido() == null) {
                PedidosDto dto = pedidosMapper.toDto(this.pedidoSeleccionado);
                pedidosService.guardarPedido(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pedido creado correctamente."));
            } else {
                ModPedidosDto modDto = new ModPedidosDto(
                        this.pedidoSeleccionado.getTotal(),
                        this.pedidoSeleccionado.getEstado(),
                        this.pedidoSeleccionado.getIdAlumno(),
                        this.pedidoSeleccionado.getIdUsuarioCredito()
                );
                pedidosService.modificarPedido(this.pedidoSeleccionado.getIdPedido(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pedido actualizado correctamente."));
            }
            cargarPedidos();
            PrimeFaces.current().executeScript("PF('ventanaModalPedido').hide()");
            PrimeFaces.current().ajax().update("formulario-pedidos:panel-pedidos", "formulario-pedidos:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el pedido", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el pedido."));
            PrimeFaces.current().ajax().update("formulario-pedidos:mensaje-emergente");
        }
    }

    public void eliminarPedido() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.pedidoSeleccionado == null || this.pedidoSeleccionado.getIdPedido() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún pedido."));
            return;
        }

        try {
            pedidosService.eliminarPedido(this.pedidoSeleccionado.getIdPedido());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pedido eliminado correctamente."));
            cargarPedidos();
            PrimeFaces.current().ajax().update("formulario-pedidos:panel-pedidos", "formulario-pedidos:mensaje-emergente");
        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar un pedido con datos relacionados. ID: {}", this.pedidoSeleccionado.getIdPedido(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar el pedido."));
            PrimeFaces.current().ajax().update("formulario-pedidos:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el pedido con ID: {}", this.pedidoSeleccionado.getIdPedido(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el pedido."));
            PrimeFaces.current().ajax().update("formulario-pedidos:mensaje-emergente");
        }
    }

    public EstadoPedido[] getEstadosPedido() {
        return EstadoPedido.values();
    }
}
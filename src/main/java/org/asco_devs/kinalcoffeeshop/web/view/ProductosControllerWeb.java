package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.CategoriaProductosService;
import org.asco_devs.kinalcoffeeshop.domain.service.ProductosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaProductos;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CategoriaProductosMapper;
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

@Named("productosControllerWeb")
@ViewScoped
@Getter
@Setter
public class ProductosControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProductosControllerWeb.class);

    @Autowired private ProductosService productosService;
    @Autowired private CategoriaProductosService categoriaProductosService;
    @Autowired private ProductosMapper productosMapper;
    @Autowired private CategoriaProductosMapper categoriaProductosMapper;

    private List<Productos> productos;
    private Productos productoSeleccionado;
    private List<CategoriaProductos> listaCategorias;

    @PostConstruct
    public void init() {
        cargarListasDesplegables();
        cargarProductos();
        this.productoSeleccionado = new Productos();
    }

    private void cargarListasDesplegables() {
        this.listaCategorias = categoriaProductosService.obtenerTodo().stream()
                .map(categoriaProductosMapper::toEntity)
                .collect(Collectors.toList());
    }

    private void cargarProductos() {
        Map<Long, CategoriaProductos> categoriaMap = listaCategorias.stream()
                .collect(Collectors.toMap(CategoriaProductos::getIdCategoria, Function.identity()));

        this.productos = productosService.obtenerTodo().stream()
                .map(productosMapper::toEntity)
                .peek(producto -> producto.setCategoria(categoriaMap.get(producto.getIdCategoria())))
                .collect(Collectors.toList());
    }

    public void prepararNuevoProducto() {
        this.productoSeleccionado = new Productos();
    }

    public void guardarProducto() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.productoSeleccionado.getCategoria() != null) {
                this.productoSeleccionado.setIdCategoria(this.productoSeleccionado.getCategoria().getIdCategoria());
            }

            if (this.productoSeleccionado.getIdProducto() == null) {
                ProductosDto dto = productosMapper.toDto(this.productoSeleccionado);
                productosService.guardarProducto(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto creado correctamente."));
            } else {
                ModProductosDto modDto = new ModProductosDto(
                        this.productoSeleccionado.getNombre(),
                        this.productoSeleccionado.getDescripcion(),
                        this.productoSeleccionado.getPrecio(),
                        this.productoSeleccionado.getStock(),
                        this.productoSeleccionado.getFechaDeExpiracion(),
                        this.productoSeleccionado.getIdCategoria()
                );
                productosService.modificarProducto(this.productoSeleccionado.getIdProducto(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto actualizado correctamente."));
            }
            cargarProductos();
            PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
            PrimeFaces.current().ajax().update("formulario-productos:panel-productos", "formulario-productos:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar el producto", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el producto."));
            PrimeFaces.current().ajax().update("formulario-productos:mensaje-emergente");
        }
    }

    public void eliminarProducto() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.productoSeleccionado == null || this.productoSeleccionado.getIdProducto() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ningún producto."));
            return;
        }

        try {
            productosService.eliminarProducto(this.productoSeleccionado.getIdProducto());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto eliminado correctamente."));
            cargarProductos();
            PrimeFaces.current().ajax().update("formulario-productos:panel-productos", "formulario-productos:mensaje-emergente");

        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar un producto con datos relacionados. ID: {}", this.productoSeleccionado.getIdProducto(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar el producto."));
            PrimeFaces.current().ajax().update("formulario-productos:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el producto con ID: {}", this.productoSeleccionado.getIdProducto(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el producto."));
            PrimeFaces.current().ajax().update("formulario-productos:mensaje-emergente");
        }
    }
}
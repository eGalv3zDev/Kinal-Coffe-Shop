package org.asco_devs.kinalcoffeeshop.web.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.CategoriaProductosService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaProductos;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CategoriaProductosMapper;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("categoriaControllerWeb")
@ViewScoped
@Getter
@Setter
public class CategoriaControllerWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CategoriaControllerWeb.class);

    @Autowired
    private CategoriaProductosService categoriaProductosService;
    @Autowired
    private CategoriaProductosMapper categoriaProductosMapper;

    private List<CategoriaProductos> categorias;
    private CategoriaProductos categoriaSeleccionada;

    @PostConstruct
    public void init() {
        cargarCategorias();
        this.categoriaSeleccionada = new CategoriaProductos();
    }

    private void cargarCategorias() {
        this.categorias = categoriaProductosService.obtenerTodo().stream()
                .map(categoriaProductosMapper::toEntity)
                .collect(Collectors.toList());
    }

    public void prepararNuevaCategoria() {
        this.categoriaSeleccionada = new CategoriaProductos();
    }

    public void guardarCategoria() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.categoriaSeleccionada.getIdCategoria() == null) {
                CategoriaProductosDto dto = categoriaProductosMapper.toDto(this.categoriaSeleccionada);
                categoriaProductosService.guardarCategoria(dto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría creada correctamente."));
            } else {
                ModCategoriaProductosDto modDto = new ModCategoriaProductosDto(this.categoriaSeleccionada.getNombre());
                categoriaProductosService.modificarCategoria(this.categoriaSeleccionada.getIdCategoria(), modDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría actualizada correctamente."));
            }

            cargarCategorias();
            PrimeFaces.current().executeScript("PF('ventanaModalCategoria').hide()");
            PrimeFaces.current().ajax().update("formulario-categorias:panel-categorias", "formulario-categorias:mensaje-emergente");

        } catch (Exception e) {
            log.error("Ocurrió un error al guardar la categoría", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar la categoría."));
            PrimeFaces.current().ajax().update("formulario-categorias:mensaje-emergente");
        }
    }

    public void eliminarCategoria() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.categoriaSeleccionada == null || this.categoriaSeleccionada.getIdCategoria() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Crítico", "No se ha seleccionado ninguna categoría para eliminar."));
            return;
        }

        try {
            categoriaProductosService.eliminarCategoria(this.categoriaSeleccionada.getIdCategoria());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría eliminada correctamente."));

            cargarCategorias();
            PrimeFaces.current().ajax().update("formulario-categorias:panel-categorias", "formulario-categorias:mensaje-emergente");

        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de eliminar categoría en uso. ID: {}", this.categoriaSeleccionada.getIdCategoria(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acción Bloqueada", "No se puede eliminar. La categoría está asignada a productos."));
            PrimeFaces.current().ajax().update("formulario-categorias:mensaje-emergente");
        } catch (Exception e) {
            log.error("Error inesperado al eliminar la categoría con ID: {}", this.categoriaSeleccionada.getIdCategoria(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la categoría."));
            PrimeFaces.current().ajax().update("formulario-categorias:mensaje-emergente");
        }
    }
}
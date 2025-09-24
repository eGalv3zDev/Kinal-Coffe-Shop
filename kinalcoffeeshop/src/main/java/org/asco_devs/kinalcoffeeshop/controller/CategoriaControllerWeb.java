package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.CategoriaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Data
@Named("categoriaControllerWeb")
public class CategoriaControllerWeb implements Serializable {

    @Autowired
    CategoriaService categoriaService;

    private List<CategoriaDto> categorias;
    private CategoriaDto categoriaSeleccionada;
    private static final Logger logger = LoggerFactory.getLogger(CategoriaControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarCategorias();
    }

    public void cargarCategorias() {
        this.categorias = this.categoriaService.obtenerCategorias();
        this.categorias.forEach(categoria -> logger.info("Categoria cargada: " + categoria.toString()));
    }

    public void agregarCategoria() {
        this.categoriaSeleccionada = new CategoriaDto(null, null);
    }

    public void guardarCategoria() {
        logger.info("Categoria a guardar: " + this.categoriaSeleccionada);

        if (this.categoriaSeleccionada.id() == null) {
            this.categoriaService.guardarCategoria(this.categoriaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría agregada exitosamente."));
        } else {
            ModCategoriaDto modDto = new ModCategoriaDto(this.categoriaSeleccionada.name());
            this.categoriaService.modificarCategoria(this.categoriaSeleccionada.id(), modDto);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría actualizada exitosamente."));
        }

        cargarCategorias();

        PrimeFaces.current().executeScript("PF('ventanaModalCategoria').hide()");
        PrimeFaces.current().ajax().update("formulario-categorias:mensaje-emergente",
                "formulario-categorias:tabla-categorias");
        this.categoriaSeleccionada = null;
    }

    public void eliminarCategoria() {
        logger.info("Categoria a eliminar: " + this.categoriaSeleccionada);
        this.categoriaService.eliminarCategoria(this.categoriaSeleccionada.id());
        this.categoriaSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría eliminada exitosamente."));

        cargarCategorias();

        PrimeFaces.current().ajax().update("formulario-categorias:mensaje-emergente",
                "formulario-categorias:tabla-categorias");
    }
}

package org.asco_devs.kinalcoffeeshop.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.CategoriaDtoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Named("categoriaControllerWeb")
@ViewScoped
@Getter
@Setter
public class CategoriaControllerWeb implements Serializable {

    private List<CategoriaDtoWeb> categorias;
    private CategoriaDtoWeb categoriaSeleccionada;


    @PostConstruct
    public void init() {
        categorias = new ArrayList<>();
        // Datos de ejemplo para la demostración
        categorias.add(new CategoriaDtoWeb(1L, "Cafés Calientes"));
        categorias.add(new CategoriaDtoWeb(2L, "Bebidas Frías"));
        categorias.add(new CategoriaDtoWeb(3L, "Postres y Repostería"));
        categorias.add(new CategoriaDtoWeb(4L, "Sandwiches y Snacks"));

        this.categoriaSeleccionada = new CategoriaDtoWeb();
    }

    public void agregarCategoria() {
        this.categoriaSeleccionada = new CategoriaDtoWeb();
    }

    public void guardarCategoria() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (categoriaSeleccionada.getName() == null || categoriaSeleccionada.getName().trim().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre no puede estar vacío."));
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        if (categoriaSeleccionada.getId() == null) {
            categoriaSeleccionada.setId(generarIdUnico());
            categorias.add(categoriaSeleccionada);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría creada correctamente."));
        } else {
            int index = -1;
            for (int i = 0; i < categorias.size(); i++) {
                if (Objects.equals(categorias.get(i).getId(), categoriaSeleccionada.getId())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                categorias.set(index, categoriaSeleccionada);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría actualizada correctamente."));
            }
        }
    }

    public void eliminarCategoria() {
        categorias.remove(categoriaSeleccionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría eliminada correctamente."));
        this.categoriaSeleccionada = new CategoriaDtoWeb();
    }

    private Long generarIdUnico() {
        return ThreadLocalRandom.current().nextLong(100, 10000);
    }
}
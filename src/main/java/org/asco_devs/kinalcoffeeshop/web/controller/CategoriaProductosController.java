package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.CategoriaProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias-productos")
public class CategoriaProductosController {

    private final CategoriaProductosService categoriaProductosService;

    public CategoriaProductosController(CategoriaProductosService categoriaProductosService) {
        this.categoriaProductosService = categoriaProductosService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProductosDto>> obtenerTodas() {
        return ResponseEntity.ok(categoriaProductosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProductosService.buscarPorCodigo(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaProductosDto> crear(@Valid @RequestBody CategoriaProductosDto categoriaProductosDto) {
        return new ResponseEntity<>(categoriaProductosService.guardarCategoria(categoriaProductosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModCategoriaProductosDto modDto) {
        return ResponseEntity.ok(categoriaProductosService.modificarCategoria(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaProductosService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
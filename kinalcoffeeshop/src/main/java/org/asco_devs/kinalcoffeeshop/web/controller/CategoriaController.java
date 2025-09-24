package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
@Tag(name="CategoriaController", description = "API de gestion de categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List> obtenerCategorias() {
        return ResponseEntity.ok(this.categoriaService.obtenerCategorias());
    }

    @GetMapping("{idCategoria}")
    @Operation(
            summary = "Buscar una categoria por su codigo",
            description = "Retorna una categoria segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
                    @ApiResponse(responseCode = "404", description = "Categoria no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<CategoriaDto> obtenerPorId(@Parameter(description = "Identificador de la Categoria a recuperar", example = "1")
                                                  @PathVariable Long idCategoria) {
        return  ResponseEntity.ok(this.categoriaService.buscarPorId(idCategoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> guardarCategoria(@RequestBody @Valid CategoriaDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.guardarCategoria(dto));
    }

    @PutMapping("{idCategoria}")
    public ResponseEntity<CategoriaDto> modificarCategoria(@PathVariable Long idCategoria, @RequestBody ModCategoriaDto mod){
        return ResponseEntity.ok(this.categoriaService.modificarCategoria(idCategoria, mod));
    }

    @DeleteMapping("{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long idCategoria){
        this.categoriaService.eliminarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }
}

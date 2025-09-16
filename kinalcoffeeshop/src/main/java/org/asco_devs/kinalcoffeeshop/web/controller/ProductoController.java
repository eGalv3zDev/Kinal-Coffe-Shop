package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/productos")
@Tag(name="ProductoController", description = "API de gestion de productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List> obtenerProductos(){
        return ResponseEntity.ok(this.productoService.obtenerProductos());
    }

    @GetMapping("{idProducto}")
    @Operation(
            summary = "Buscar un producto por su codigo",
            description = "Retorna un producto segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<ProductoDto> buscarPorCodigo(@Parameter(description = "Identificador del producto a recuperar", example = "1")
                                                           @PathVariable Long idProducto){
        return ResponseEntity.ok(this.productoService.buscarPorId(idProducto));
    }

    @PostMapping
    public ResponseEntity<ProductoDto> guardarProducto(@RequestBody @Valid ProductoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoService.guardarProducto(dto));
    }

    @PutMapping("{idProducto}")
    public ResponseEntity<ProductoDto> modificarProducto(@PathVariable Long idProducto, @RequestBody ModProductoDto mod){
        return ResponseEntity.ok(this.productoService.modificarProducto(idProducto, mod));
    }

    @DeleteMapping("{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto){
        this.productoService.eliminarProducto(idProducto);
        return ResponseEntity.noContent().build();
    }
}

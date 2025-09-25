package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ProductosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.ProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public ResponseEntity<List<ProductosDto>> obtenerTodos() {
        return ResponseEntity.ok(productosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productosService.buscarPorCodigo(id));
    }

    @PostMapping
    public ResponseEntity<ProductosDto> crear(@Valid @RequestBody ProductosDto productosDto) {
        return new ResponseEntity<>(productosService.guardarProducto(productosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModProductosDto modDto) {
        return ResponseEntity.ok(productosService.modificarProducto(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productosService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
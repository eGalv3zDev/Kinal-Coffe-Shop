package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.DetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.service.DetalleFacturasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/detalles-facturas")
public class DetalleFacturasController {

    private final DetalleFacturasService detalleFacturasService;

    public DetalleFacturasController(DetalleFacturasService detalleFacturasService) {
        this.detalleFacturasService = detalleFacturasService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleFacturasDto>> obtenerTodos() {
        return ResponseEntity.ok(detalleFacturasService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFacturasDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detalleFacturasService.buscarPorCodigo(id));
    }

    // Endpoint anidado para obtener todos los detalles de una factura específica
    @GetMapping("/factura/{idFactura}")
    public ResponseEntity<List<DetalleFacturasDto>> obtenerPorFactura(@PathVariable Long idFactura) {
        return ResponseEntity.ok(detalleFacturasService.buscarPorFactura(idFactura));
    }

    @PostMapping
    public ResponseEntity<DetalleFacturasDto> crear(@Valid @RequestBody DetalleFacturasDto detalleFacturasDto) {
        return new ResponseEntity<>(detalleFacturasService.guardarDetalle(detalleFacturasDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFacturasDto> modificar(@PathVariable Long id, @Valid @RequestBody ModDetalleFacturasDto modDto) {
        return ResponseEntity.ok(detalleFacturasService.modificarDetalle(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleFacturasService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
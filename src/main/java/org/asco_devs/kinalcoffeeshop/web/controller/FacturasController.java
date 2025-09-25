package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.FacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModFacturasDto;
import org.asco_devs.kinalcoffeeshop.domain.service.FacturasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas")
public class FacturasController {

    private final FacturasService facturasService;

    public FacturasController(FacturasService facturasService) {
        this.facturasService = facturasService;
    }

    @GetMapping
    public ResponseEntity<List<FacturasDto>> obtenerTodas() {
        return ResponseEntity.ok(facturasService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturasDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturasService.buscarPorCodigo(id));
    }

    // Endpoint para buscar la factura de un pedido específico
    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<FacturasDto> obtenerPorPedido(@PathVariable Long idPedido) {
        return facturasService.buscarPorPedido(idPedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FacturasDto> crear(@Valid @RequestBody FacturasDto facturasDto) {
        return new ResponseEntity<>(facturasService.guardarFactura(facturasDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturasDto> modificar(@PathVariable Long id, @Valid @RequestBody ModFacturasDto modDto) {
        return ResponseEntity.ok(facturasService.modificarFactura(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturasService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }
}
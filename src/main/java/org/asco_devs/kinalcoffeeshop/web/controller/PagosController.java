package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModPagosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PagosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.PagosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagosController {

    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @GetMapping
    public ResponseEntity<List<PagosDto>> obtenerTodos() {
        return ResponseEntity.ok(pagosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagosService.buscarPorCodigo(id));
    }

    // Endpoint para obtener todos los pagos de un pedido específico
    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<PagosDto>> obtenerPorPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(pagosService.buscarPorPedido(idPedido));
    }

    @PostMapping
    public ResponseEntity<PagosDto> crear(@Valid @RequestBody PagosDto pagosDto) {
        return new ResponseEntity<>(pagosService.guardarPago(pagosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModPagosDto modDto) {
        return ResponseEntity.ok(pagosService.modificarPago(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagosService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
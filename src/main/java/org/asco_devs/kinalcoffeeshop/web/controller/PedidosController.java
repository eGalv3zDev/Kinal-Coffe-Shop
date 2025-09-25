package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModPedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.PedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.PedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public ResponseEntity<List<PedidosDto>> obtenerTodos() {
        return ResponseEntity.ok(pedidosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidosService.buscarPorCodigo(id));
    }

    @PostMapping
    public ResponseEntity<PedidosDto> crear(@Valid @RequestBody PedidosDto pedidosDto) {
        return new ResponseEntity<>(pedidosService.guardarPedido(pedidosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModPedidosDto modDto) {
        return ResponseEntity.ok(pedidosService.modificarPedido(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidosService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
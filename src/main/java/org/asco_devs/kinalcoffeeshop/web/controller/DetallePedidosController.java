package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.DetallePedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/detalles-pedidos")
public class DetallePedidosController {

    private final DetallePedidosService detallePedidosService;

    public DetallePedidosController(DetallePedidosService detallePedidosService) {
        this.detallePedidosService = detallePedidosService;
    }

    // Endpoint para obtener todos los detalles de todos los pedidos
    @GetMapping
    public ResponseEntity<List<DetallePedidosDto>> obtenerTodos() {
        return ResponseEntity.ok(detallePedidosService.obtenerTodo());
    }

    // Endpoint para obtener un detalle específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detallePedidosService.buscarPorCodigo(id));
    }

    // Endpoint anidado para obtener todos los detalles de un pedido específico
    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<DetallePedidosDto>> obtenerPorPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(detallePedidosService.buscarPorPedido(idPedido));
    }

    @PostMapping
    public ResponseEntity<DetallePedidosDto> crear(@Valid @RequestBody DetallePedidosDto detallePedidosDto) {
        return new ResponseEntity<>(detallePedidosService.guardarDetalle(detallePedidosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModDetallePedidosDto modDto) {
        return ResponseEntity.ok(detallePedidosService.modificarDetalle(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detallePedidosService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
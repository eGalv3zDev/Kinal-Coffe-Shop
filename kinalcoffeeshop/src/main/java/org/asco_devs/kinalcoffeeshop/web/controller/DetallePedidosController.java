package org.asco_devs.kinalcoffeeshop.web.controller;

import org.asco_devs.kinalcoffeeshop.dominio.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.exception.DetallePedidosExistsException;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetallePedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallepedidos")
public class DetallePedidosController {

    private final DetallePedidosService detallePedidosService;

    public DetallePedidosController(DetallePedidosService detallePedidosService) {
        this.detallePedidosService = detallePedidosService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidosDto>> obtenerTodo() {
        return ResponseEntity.ok(this.detallePedidosService.obtenerTodo());
    }

    @GetMapping("{idDetalle}")
    public ResponseEntity<DetallePedidosDto> buscarPorId(@PathVariable Long idDetalle) {
        return ResponseEntity.ok(this.detallePedidosService.buscarPorId(idDetalle));
    }

    @PostMapping
    public ResponseEntity<DetallePedidosDto> guardarDetalle(@RequestBody DetallePedidosDto detalle) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.detallePedidosService.guardarDetalle(detalle));
    }

    @PutMapping("{idDetalle}")
    public ResponseEntity<DetallePedidosDto> modificarDetalle(@PathVariable Long idDetalle,
                                                              @RequestBody ModDetallePedidosDto modDetalle) {
        return ResponseEntity.ok(this.detallePedidosService.modificarDetalle(idDetalle, modDetalle));
    }

    @DeleteMapping("{idDetalle}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long idDetalle) {
        this.detallePedidosService.eliminarDetalle(idDetalle);
        return ResponseEntity.ok().build();
    }
}
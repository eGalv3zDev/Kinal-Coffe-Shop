package org.asco_devs.kinalcoffeeshop.web.controller;

import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/detallepedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.detallePedidoService.obtenerTodo());
    }

    @GetMapping("{idDetalle}")
    public ResponseEntity<DetallePedidoDto> buscarPorId(@PathVariable Long idDetalle) {
        return ResponseEntity.ok(this.detallePedidoService.buscarPorId(idDetalle));
    }

    @PostMapping
    public ResponseEntity<DetallePedidoDto> guardarDetalle(@RequestBody DetallePedidoDto detalle) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.detallePedidoService.guardarDetalle(detalle));
    }

    @PutMapping("{idDetalle}")
    public ResponseEntity<DetallePedidoDto> modificarDetalle(@PathVariable Long idDetalle,
                                                             @RequestBody ModDetallePedidoDto modDetalle) {
        return ResponseEntity.ok(this.detallePedidoService.modificarDetalle(idDetalle, modDetalle));
    }

    @DeleteMapping("{idDetalle}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long idDetalle) {
        this.detallePedidoService.eliminarDetalle(idDetalle);
        return ResponseEntity.ok().build();
    }
}
package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/detallepedidos")
public class DetallePedidoController {

    @Autowired
    private final DetallePedidoService service;

    public DetallePedidoController(DetallePedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List> obtenerDetallePedidos() {
        return ResponseEntity.ok(this.service.obtenerDetallePedidos());
    }

    @GetMapping("{idDetalle}")
    @Operation(
            summary = "Buscar un detalle de pedido por su ID",
            description = "Retorna un detalle de pedido segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalle de pedido encontrado"),
                    @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<DetallePedidoDto> obtenerPorId(@Parameter(description = "Identificador de el Detalle de el Pedido a recuperar", example = "1")
                                                          @PathVariable Long idDetalle) {
        return  ResponseEntity.ok(this.service.buscarPorId(idDetalle));
    }

    @PostMapping
    public ResponseEntity<DetallePedidoDto> guardarDetalle(@RequestBody @Valid DetallePedidoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.service.guardarDetalle(dto));
    }

    @PutMapping("{idDetalle}")
    public ResponseEntity<DetallePedidoDto> modificarDetalle(@PathVariable Long idDetalle,
                                                             @RequestBody ModDetallePedidoDto mod) {
        return ResponseEntity.ok(this.service.modificarDetalle(idDetalle, mod));
    }

    @DeleteMapping("{idDetalle}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long idDetalle) {
        this.service.eliminarDetalle(idDetalle);
        return ResponseEntity.ok().build();
    }
}
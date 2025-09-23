package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.PedidoConDetallesDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.PedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
@Tag(name = "PedidoController", description = "API de gestion de pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> obtenerPedidos() {
        return ResponseEntity.ok(this.pedidoService.obtenerPedidos());
    }

    @GetMapping("{idPedido}")
    @Operation(
            summary = "Buscar un pedido por su codigo",
            description = "Retorna un pedido segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<PedidoDto> buscarPorCodigo(@Parameter(description = "Identificador del pedido a recuperar", example = "1")
                                                     @PathVariable Long idPedido) {
        return ResponseEntity.ok(this.pedidoService.buscarPorId(idPedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> guardarPedido(@RequestBody @Valid PedidoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.guardarPedido(dto));
    }

    @PutMapping("{idPedido}")
    public ResponseEntity<PedidoDto> modificarPedido(@PathVariable Long idPedido, @RequestBody @Valid ModPedidoDto mod) {
        return ResponseEntity.ok(this.pedidoService.modificarPedido(idPedido, mod));
    }

    @DeleteMapping("{idPedido}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long idPedido) {
        this.pedidoService.eliminarPedido(idPedido);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/alumno/{nombre}")
    public List<PedidoConDetallesDto> getPedidosPorAlumno(@PathVariable String nombre) {
        return pedidoService.obtenerPedidosPorAlumno(nombre);
    }
}
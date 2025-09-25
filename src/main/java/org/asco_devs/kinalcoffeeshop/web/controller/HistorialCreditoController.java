package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito.HistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito.ModHistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.HistorialCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/historiales-credito")
@Tag(name = "HistorialCreditoController", description = "API de gestion de historiales de credito")
public class HistorialCreditoController {
    @Autowired
    private HistorialCreditoService service;

    public HistorialCreditoController(HistorialCreditoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List> obtenerHistorialesCredito() {
        return ResponseEntity.ok(this.service.obtenerHistoriales());
    }

    @GetMapping("{idHistorial}")
    @Operation(
            summary = "Buscar un historial de credito por su codigo",
            description = "Retorna un historial de credito segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Historial de credito encontrado"),
                    @ApiResponse(responseCode = "404", description = "Historial de credito no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<HistorialCreditoDto> buscarPorId(@PathVariable Long idHistorial) {
        return ResponseEntity.ok(this.service.buscarPorId(idHistorial));
    }

    @PostMapping
    public ResponseEntity<HistorialCreditoDto> guardarHistorialCredito(@RequestBody HistorialCreditoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.guardarHistorial(dto));
    }

    @PutMapping("{idHistorial}")
    public ResponseEntity <HistorialCreditoDto> modificarHistorialCredito(@PathVariable Long idHistorial, @RequestBody ModHistorialCreditoDto mod) {
        return ResponseEntity.ok(this.service.modificarHistorial(idHistorial, mod));
    }

    @DeleteMapping("{idHistorial}")
    public ResponseEntity<Void> eliminarHistorialCredito(@PathVariable Long idHistorial) {
        this.service.eliminarHistorial(idHistorial);
        return ResponseEntity.noContent().build();
    }
}

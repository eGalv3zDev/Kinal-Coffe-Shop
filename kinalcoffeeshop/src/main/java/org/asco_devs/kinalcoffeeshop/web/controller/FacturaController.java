package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/facturas")
@Tag(name="FacturaController", description = "API de gestion de facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List> obtenerFacturas() {
        return ResponseEntity.ok(this.facturaService.obtenerFacturas());
    }

    @GetMapping("{idFactura}")
    @Operation(
            summary = "Buscar una factura por su codigo",
            description = "Retorna una factura segun su codigo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Factura encontrada"),
                    @ApiResponse(responseCode = "404", description = "Factura no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<FacturaDto> obtenerPorId(@Parameter(description = "Identificador de la Factura a recuperar", example = "1")
                                                    @PathVariable Long idFactura) {
        return  ResponseEntity.ok(this.facturaService.buscarFacturaPorId(idFactura));
    }

    @PostMapping
    public ResponseEntity<FacturaDto> guardarFactura(@RequestBody @Valid FacturaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.facturaService.guardarFactura(dto));
    }

    @PutMapping("{idFactura}")
    public ResponseEntity<FacturaDto> modificarFactura(@PathVariable Long idFactura, @RequestBody ModFacturaDto mod) {
        return  ResponseEntity.ok(this.facturaService.modificarFactura(idFactura, mod));
    }

    @DeleteMapping("{idFactura}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long idFactura) {
        this.facturaService.eliminarFactura(idFactura);
        return ResponseEntity.noContent().build();
    }
}

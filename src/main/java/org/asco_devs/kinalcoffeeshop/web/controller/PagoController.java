package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.ModPagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pagos")
@Tag(name = "PagoController", description = "API de gestion de pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDto>> obtenerPagos() {
        return ResponseEntity.ok(this.pagoService.obtenerPagos());
    }

    @GetMapping("{idPago}")
    @Operation(
            summary = "Buscar un pago por su codigo",
            description = "Retorna un pago segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pago encontrado"),
                    @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<PagoDto> buscarPorCodigo(@Parameter(description = "Identificador del pago a recuperar", example = "1")
                                                   @PathVariable Long idPago) {
        return ResponseEntity.ok(this.pagoService.buscarPorId(idPago));
    }

    @PostMapping
    public ResponseEntity<PagoDto> guardarPago(@RequestBody @Valid PagoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pagoService.guardarPago(dto));
    }

    @PutMapping("{idPago}")
    public ResponseEntity<PagoDto> modificarPago(@PathVariable Long idPago, @RequestBody @Valid ModPagoDto mod) {
        return ResponseEntity.ok(this.pagoService.modificarPago(idPago, mod));
    }

    @DeleteMapping("{idPago}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long idPago) {
        this.pagoService.eliminarPago(idPago);
        return ResponseEntity.noContent().build();
    }
}
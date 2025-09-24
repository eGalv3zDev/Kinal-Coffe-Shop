package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.asco_devs.kinalcoffeeshop.dominio.dto.CuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.CuentaDeCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cuentas-de-credito")
@Tag(name = "CuentaDeCreditoController", description = "API de gestion de cuentas de credito")
public class CuentaDeCreditoController {
    @Autowired
    private CuentaDeCreditoService service;

    public CuentaDeCreditoController(CuentaDeCreditoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List> obtenerCuentasDeCredito() {
        return ResponseEntity.ok(this.service.obtenerCuentasDeCredito());
    }

    @GetMapping("{idCuenta}")
    @Operation(
            summary = "Buscar una cuenta de credito por su codigo",
            description = "Retorna una cuenta de credito segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cuenta de credito encontrada"),
                    @ApiResponse(responseCode = "404", description = "Cuenta de credito no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<CuentaDeCreditoDto> buscarPorId(@Parameter(description = "Identificador de la cuenta a recuperar")
                                                              @PathVariable Long idCuenta) {
        return ResponseEntity.ok(this.service.buscarPorId(idCuenta));
    }

    @PostMapping
    public ResponseEntity<CuentaDeCreditoDto> guardarCuentaDeCredito(@RequestBody CuentaDeCreditoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.guardarCuentaDeCredito(dto));
    }

    @PutMapping("{idCuenta}")
    public ResponseEntity<CuentaDeCreditoDto> modificarCuentaDeCredito(@PathVariable Long idCuenta) {
        this.service.eliminarCuentaDeCredito(idCuenta);
        return ResponseEntity.noContent().build();
    }

}


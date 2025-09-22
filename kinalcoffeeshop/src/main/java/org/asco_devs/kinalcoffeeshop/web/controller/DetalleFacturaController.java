package org.asco_devs.kinalcoffeeshop.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.DetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/detallefacturas")
@Tag(name = "DetalleFacturaController", description = "API de gestión de detalle facturas")
public class DetalleFacturaController {
    @Autowired
    private DetalleFacturaService detalleFacturaService;

    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    public ResponseEntity<List> obtenerProductos(){
        return ResponseEntity.ok(this.detalleFacturaService.obtenerDetalleFactura());
    }

    @GetMapping("{idDetalleFactura}")
    @Operation(
            summary = "Buscar un detalle de alguna factura por su codigo",
            description = "Retorna un detalle de alguna factura segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "DetalleFactura encontrado"),
                    @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<DetalleFacturaDto> buscarPorId(@Parameter(description = "Identificador del detalle de la factura a recuperar", example = "1")
                                                       @PathVariable Long idDetalleFactura){
        return ResponseEntity.ok(this.detalleFacturaService.buscarPorId(idDetalleFactura));
    }

    @PostMapping
    public ResponseEntity<DetalleFacturaDto> guardarDetalleFactuRA(@RequestBody @Valid DetalleFacturaDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detalleFacturaService.guardarDetalleFactura(dto));
    }

    @PutMapping("{idDetalleFactura}")
    public ResponseEntity<DetalleFacturaDto> modificarDetalleFactura(@PathVariable Long idDetalleFactura, @RequestBody ModDetalleFacturaDto mod){
        return ResponseEntity.ok(this.detalleFacturaService.modificarDetalleFactura(idDetalleFactura, mod));
    }

    @DeleteMapping("{idDetalleFactura}")
    public ResponseEntity<Void> eliminarDetalleFactura(@PathVariable Long idDetalleFactura){
        this.detalleFacturaService.eliminarDetalleFactura(idDetalleFactura);
        return ResponseEntity.noContent().build();
    }
}

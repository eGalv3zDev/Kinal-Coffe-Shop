package org.asco_devs.kinalcoffeeshop.web.controller;

import org.asco_devs.kinalcoffeeshop.dominio.dto.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModLineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.LineaDeCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/lineas-de-credito")
public class LineaDeCreditoController {
    private final LineaDeCreditoService service;

    public LineaDeCreditoController(LineaDeCreditoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LineaDeCreditoDto>> obtenerLineasDeCredito() {
        return ResponseEntity.ok(this.service.obtenerLineasDeCredito());
    }

    @GetMapping("{idConsumo}")
    public ResponseEntity<LineaDeCreditoDto> buscarPorId(@PathVariable Long idConsumo) {
        return ResponseEntity.ofNullable(this.service.buscarPorId(idConsumo));
    }

    @PostMapping
    public ResponseEntity<LineaDeCreditoDto> guardarLineaDeCredito(@RequestBody LineaDeCreditoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.guardarLineaDeCredito(dto));
    }

    @PutMapping("{idConsumo}")
    public ResponseEntity<LineaDeCreditoDto> modificarLineaDeCredito(@PathVariable Long idConsumo, @RequestBody ModLineaDeCreditoDto mod) {
        return ResponseEntity.ok(this.service.modificarLineaDeCredito(idConsumo, mod));
    }

    @DeleteMapping("{idConsumo}")
    public ResponseEntity<Void> eliminarLineaDeCredito(@PathVariable Long idConsumo) {
        this.service.eliminarLineaDeCredito(idConsumo);
        return ResponseEntity.noContent().build();
    }
}
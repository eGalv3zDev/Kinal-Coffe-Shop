package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.LineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModLineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.service.LineasDeCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lineas-credito")
public class LineasDeCreditoController {

    private final LineasDeCreditoService lineasDeCreditoService;

    public LineasDeCreditoController(LineasDeCreditoService lineasDeCreditoService) {
        this.lineasDeCreditoService = lineasDeCreditoService;
    }

    @GetMapping
    public ResponseEntity<List<LineasDeCreditoDto>> obtenerTodas() {
        return ResponseEntity.ok(lineasDeCreditoService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineasDeCreditoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lineasDeCreditoService.buscarPorCodigo(id));
    }

    // Endpoint para obtener todos los consumos de una cuenta de crédito
    @GetMapping("/cuenta/{idCuenta}")
    public ResponseEntity<List<LineasDeCreditoDto>> obtenerPorCuenta(@PathVariable Long idCuenta) {
        return ResponseEntity.ok(lineasDeCreditoService.buscarPorCuenta(idCuenta));
    }

    @PostMapping
    public ResponseEntity<LineasDeCreditoDto> crear(@Valid @RequestBody LineasDeCreditoDto lineasDeCreditoDto) {
        return new ResponseEntity<>(lineasDeCreditoService.guardarConsumo(lineasDeCreditoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineasDeCreditoDto> modificar(@PathVariable Long id, @Valid @RequestBody ModLineasDeCreditoDto modDto) {
        return ResponseEntity.ok(lineasDeCreditoService.modificarConsumo(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        lineasDeCreditoService.eliminarConsumo(id);
        return ResponseEntity.noContent().build();
    }
}
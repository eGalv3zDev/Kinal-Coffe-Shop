package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.HistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModHistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.HistorialCreditosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/historial-creditos")
public class HistorialCreditosController {

    private final HistorialCreditosService historialCreditosService;

    public HistorialCreditosController(HistorialCreditosService historialCreditosService) {
        this.historialCreditosService = historialCreditosService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialCreditosDto>> obtenerTodos() {
        return ResponseEntity.ok(historialCreditosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialCreditosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(historialCreditosService.buscarPorCodigo(id));
    }

    // Endpoint clave para consultar el historial de una cuenta específica
    @GetMapping("/cuenta/{idCuenta}")
    public ResponseEntity<List<HistorialCreditosDto>> obtenerPorCuenta(@PathVariable Long idCuenta) {
        return ResponseEntity.ok(historialCreditosService.buscarPorCuenta(idCuenta));
    }

    @PostMapping
    public ResponseEntity<HistorialCreditosDto> crear(@Valid @RequestBody HistorialCreditosDto historialCreditosDto) {
        return new ResponseEntity<>(historialCreditosService.guardarRegistro(historialCreditosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialCreditosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModHistorialCreditosDto modDto) {
        return ResponseEntity.ok(historialCreditosService.modificarRegistro(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialCreditosService.eliminarRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
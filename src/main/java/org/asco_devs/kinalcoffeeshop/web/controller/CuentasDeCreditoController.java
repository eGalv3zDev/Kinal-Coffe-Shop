package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.CuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModCuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.service.CuentasDeCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuentas-credito")
public class CuentasDeCreditoController {

    private final CuentasDeCreditoService cuentasDeCreditoService;

    public CuentasDeCreditoController(CuentasDeCreditoService cuentasDeCreditoService) {
        this.cuentasDeCreditoService = cuentasDeCreditoService;
    }

    @GetMapping
    public ResponseEntity<List<CuentasDeCreditoDto>> obtenerTodas() {
        return ResponseEntity.ok(cuentasDeCreditoService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentasDeCreditoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentasDeCreditoService.buscarPorCodigo(id));
    }

    // Endpoint para buscar la cuenta de un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<CuentasDeCreditoDto> obtenerPorUsuarioId(@PathVariable Long idUsuario) {
        return cuentasDeCreditoService.buscarPorUsuario(idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuentasDeCreditoDto> crear(@Valid @RequestBody CuentasDeCreditoDto cuentasDeCreditoDto) {
        return new ResponseEntity<>(cuentasDeCreditoService.guardarCuenta(cuentasDeCreditoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentasDeCreditoDto> modificar(@PathVariable Long id, @Valid @RequestBody ModCuentasDeCreditoDto modDto) {
        return ResponseEntity.ok(cuentasDeCreditoService.modificarCuenta(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentasDeCreditoService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModUsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.UsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.domain.service.UsuariosConCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-credito")
public class UsuariosConCreditoController {

    private final UsuariosConCreditoService usuariosConCreditoService;

    public UsuariosConCreditoController(UsuariosConCreditoService usuariosConCreditoService) {
        this.usuariosConCreditoService = usuariosConCreditoService;
    }

    @GetMapping
    public ResponseEntity<List<UsuariosConCreditoDto>> obtenerTodos() {
        return ResponseEntity.ok(usuariosConCreditoService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosConCreditoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuariosConCreditoService.buscarPorCodigo(id));
    }

    @PostMapping
    public ResponseEntity<UsuariosConCreditoDto> crear(@Valid @RequestBody UsuariosConCreditoDto usuariosConCreditoDto) {
        return new ResponseEntity<>(usuariosConCreditoService.guardarUsuario(usuariosConCreditoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosConCreditoDto> modificar(@PathVariable Long id, @Valid @RequestBody ModUsuariosConCreditoDto modDto) {
        return ResponseEntity.ok(usuariosConCreditoService.modificarUsuario(id, modDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuariosConCreditoService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
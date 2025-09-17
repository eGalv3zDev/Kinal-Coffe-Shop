package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.UsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModUsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.UsuarioConCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios-con-credito")
@Tag(name="UsuarioConCreditoController", description = "API de gestion de usuarios con credito")
public class UsuarioConCreditoController {
    @Autowired
    private UsuarioConCreditoService usuarioConCreditoService;

    public UsuarioConCreditoController(UsuarioConCreditoService usuarioConCreditoService) {
        this.usuarioConCreditoService = usuarioConCreditoService;
    }

    @GetMapping
    public ResponseEntity<List> obtenerUsuariosConCredito(){
        return ResponseEntity.ok(this.usuarioConCreditoService.obtenerUsuariosConCredito());
    }

    @GetMapping("{idUsuarioCredito}")
    @Operation(
            summary = "Buscar un usuario con credito por su codigo",
            description = "Retorna un usuario con credito segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario con credito encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario con credito no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<UsuarioConCreditoDto> buscarPorId(@Parameter(description = "Identificador del USUARIO CON CREDITO a recuperar", example = "1")
                                                            @PathVariable Long idUsuarioCredito){
        return ResponseEntity.ok(this.usuarioConCreditoService.buscarCodigo(idUsuarioCredito));
    }

    @PostMapping
    public ResponseEntity<UsuarioConCreditoDto> guardarUsuarioConCredito(@RequestBody @Valid UsuarioConCreditoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioConCreditoService.guardarUsuarioConCredito(dto));
    }

    @PutMapping("{idUsuarioCredito}")
    public ResponseEntity<UsuarioConCreditoDto> modificarUsuarioConCredito(@PathVariable Long idUsuarioCredito, @RequestBody ModUsuarioConCreditoDto mod){
        return ResponseEntity.ok(this.usuarioConCreditoService.modificarUsuarioConCredito(idUsuarioCredito, mod));
    }

    @DeleteMapping("{idUsuarioCredito}")
    public ResponseEntity<Void> eliminarUsuarioConCredito(@PathVariable Long idUsuarioCredito){
        this.usuarioConCreditoService.eliminarUsuarioConCredito(idUsuarioCredito);
        return ResponseEntity.noContent().build();
    }
}
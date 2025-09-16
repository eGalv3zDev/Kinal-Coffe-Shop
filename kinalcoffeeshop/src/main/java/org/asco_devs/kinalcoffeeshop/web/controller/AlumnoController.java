package org.asco_devs.kinalcoffeeshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.dominio.dto.AlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModAlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alumnos")
@Tag(name="AlumnoController", description = "API de gestion de alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List> obtenerAlumnos(){
        return ResponseEntity.ok(this.alumnoService.obtenerAlumnos());
    }

    @GetMapping("{idAlumno}")
    @Operation(
            summary = "Buscar un alumno por su codigo",
            description = "Retorna un alumno segun su identificador unico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Alumno encontrado"),
                    @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<AlumnoDto> buscarPorId(@Parameter(description = "Identificador del ALUMNO a recuperar", example = "1")
                                                       @PathVariable Long idAlumno){
        return ResponseEntity.ok(this.alumnoService.buscarCodigo(idAlumno));
    }

    @PostMapping
    public ResponseEntity<AlumnoDto> guardarAlumno(@RequestBody @Valid AlumnoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.alumnoService.guardarAlumno(dto));
    }

    @PutMapping("{idAlumno}")
    public ResponseEntity<AlumnoDto> modificarAlumno(@PathVariable Long idAlumno, @RequestBody ModAlumnoDto mod){
        return ResponseEntity.ok(this.alumnoService.modificarAlumno(idAlumno, mod));
    }

    @DeleteMapping("{idAlumno}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long idAlumno){
        this.alumnoService.eliminarAlumno(idAlumno);
        return ResponseEntity.noContent().build();
    }
}

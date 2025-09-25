package org.asco_devs.kinalcoffeeshop.web.controller;

import jakarta.validation.Valid;
import org.asco_devs.kinalcoffeeshop.domain.dto.AlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.dto.ModAlumnosDto;
import org.asco_devs.kinalcoffeeshop.domain.service.AlumnosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnosController {

    private final AlumnosService alumnosService;

    public AlumnosController(AlumnosService alumnosService) {
        this.alumnosService = alumnosService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnosDto>> obtenerTodos() {
        return ResponseEntity.ok(alumnosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnosDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alumnosService.buscarPorCodigo(id));
    }

    @PostMapping
    public ResponseEntity<AlumnosDto> crear(@Valid @RequestBody AlumnosDto alumnosDto) {
        return new ResponseEntity<>(alumnosService.guardarAlumno(alumnosDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnosDto> modificar(@PathVariable Long id, @Valid @RequestBody ModAlumnosDto modAlumnosDto) {
        return ResponseEntity.ok(alumnosService.modificarAlumno(id, modAlumnosDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        alumnosService.eliminarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}
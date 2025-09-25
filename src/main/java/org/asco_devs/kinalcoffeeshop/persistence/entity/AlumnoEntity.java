package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="Alumnos")
@Data
public class AlumnoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumno;
    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;
    @Column(name = "apellido", length = 64, nullable = false)
    private String apellido;
    @Column(name = "carnet", length = 16, unique = true, nullable = false)
    private String carnet;
    @Column(name = "correo", length = 128)
    private String correo;
    @Column(name = "genero", length = 10, nullable = false)
    private String genero;
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "contrasena", length = 128, nullable = false)
    private String contrasena;
}

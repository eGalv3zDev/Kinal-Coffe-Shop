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
    @Column(length = 64, nullable = false)
    private String nombre;
    @Column(length = 64, nullable = false)
    private String apellido;
    @Column(length = 16, unique = true, nullable = false)
    private String carnet;
    @Column(length = 128)
    private String correo;
    @Column(length = 10, nullable = false)
    private String genero;
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;
    @Column(length = 128, nullable = false)
    private String contraseña;
}

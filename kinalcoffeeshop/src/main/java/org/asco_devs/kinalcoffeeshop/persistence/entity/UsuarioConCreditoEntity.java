package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="usuarios_con_credito")
@Data
public class UsuarioConCreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioCredito;
    @Column(length = 64, nullable = false)
    private String nombre;
    @Column(length = 64, nullable = false)
    private String apellido;
    @Column(length = 16)
    private String telefono;
    @Column(length = 128, unique = true)
    private String correo;
    @Column(length = 10, nullable = false)
    private String genero;
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;
    @Column(length = 128, nullable = false)
    private String contraseña;
}
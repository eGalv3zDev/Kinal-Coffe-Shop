package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.domain.model.Genero;
import java.time.LocalDate;

@Entity
@Table(name = "UsuariosConCredito")
@Data
public class UsuariosConCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarioCredito")
    private Long idUsuarioCredito;

    @Column(nullable = false, length = 64)
    private String nombre;

    @Column(nullable = false, length = 64)
    private String apellido;

    @Column(length = 16)
    private String telefono;

    @Column(unique = true, length = 128)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 128)
    private String contrasena;
}
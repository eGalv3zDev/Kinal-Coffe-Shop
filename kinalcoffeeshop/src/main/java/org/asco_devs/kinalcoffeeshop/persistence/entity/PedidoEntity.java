package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "estado", length = 15, nullable = false)
    private String estado;

    @Column(name = "idAlumno", nullable = true)
    private Long idAlumno;

    @Column(name = "idUsuarioCredito", nullable = true)
    private Long idUsuarioCredito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno", insertable = false, updatable = false)
    private AlumnoEntity alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioCredito", insertable = false, updatable = false)
    private UsuarioConCreditoEntity usuarioConCredito;
}
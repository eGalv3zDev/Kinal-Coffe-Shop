package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pedidos")
@Getter
@Setter
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    @ToString.Exclude // Excluye esta relación del toString()
    private AlumnoEntity idAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioCredito")
    @ToString.Exclude // Excluye esta relación del toString()
    private UsuarioConCreditoEntity idUsuarioCredito;

    @OneToMany(mappedBy = "idPedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude // Excluye esta lista del toString()
    private List<DetallePedidoEntity> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "idFactura", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Excluye esta lista del toString()
    private List<FacturasEntity> facturas = new ArrayList<>();
}
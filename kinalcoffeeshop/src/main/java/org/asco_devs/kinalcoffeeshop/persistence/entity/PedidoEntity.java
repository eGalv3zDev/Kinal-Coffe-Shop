package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    private AlumnoEntity idAlumno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioCredito")
    private UsuarioConCreditoEntity idUsuarioCredito;
    @OneToMany(mappedBy = "idPedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallePedidoEntity> detalles = new ArrayList<>();
    @OneToMany(mappedBy = "idFactura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturasEntity> facturas = new ArrayList<>();
}
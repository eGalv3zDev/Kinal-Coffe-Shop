package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "LineasDeCredito")
@Data
public class LineaDeCreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsumo")
    private Long idConsumo;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "idCuenta", nullable = false)
    private CuentaDeCreditoEntity cuenta;
}
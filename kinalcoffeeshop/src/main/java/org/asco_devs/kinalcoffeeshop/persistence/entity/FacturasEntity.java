package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Facturas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;
    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;
    @Column(name = "total", precision = 3, scale = 2, nullable = false)
    private BigDecimal total;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido", nullable = false)
    private PedidoEntity idPedido;

}

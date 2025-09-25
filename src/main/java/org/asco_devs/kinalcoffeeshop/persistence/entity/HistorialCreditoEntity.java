package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "HistorialCreditos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialCreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @Column(name = "tipoMovimiento", nullable = false)
    private String tipoMovimiento;
    @Column(name = "monto", precision = 3, scale = 2, nullable = false)
    private BigDecimal monto;
    @ManyToOne
    @JoinColumn(name = "idConsumo", nullable = false)
    private LineaDeCreditoEntity idConsumo;
    @ManyToOne
    @JoinColumn(name = "idPago", nullable = false)
    private PagoEntity idPago;

}
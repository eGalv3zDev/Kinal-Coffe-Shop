package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.asco_devs.kinalcoffeeshop.domain.model.TipoMovimientoCredito;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistorialCreditos")
@Data
public class HistorialCreditos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorial")
    private Long idHistorial;

    @Column(name = "idCuenta", nullable = false)
    private Long idCuenta;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimientoCredito tipoMovimiento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "idConsumo")
    private Long idConsumo;

    @Column(name = "idPago")
    private Long idPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCuenta", insertable = false, updatable = false)
    private CuentasDeCredito cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConsumo", insertable = false, updatable = false)
    private LineasDeCredito consumo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPago", insertable = false, updatable = false)
    private Pagos pago;
}
package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "CuentasDeCredito")
@Data
public class CuentaDeCreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;
    @Column(name = "saldo", precision = 3, scale = 2, nullable = false)
    private BigDecimal saldo;
    @ManyToOne
    @JoinColumn(name = "idUsuarioCredito", nullable = false)
    private UsuarioConCreditoEntity idUsuarioCredito;
}

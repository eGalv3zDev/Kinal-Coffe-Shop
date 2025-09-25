package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "CuentasDeCredito")
@Data
public class CuentasDeCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCuenta")
    private Long idCuenta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(name = "idUsuarioCredito", nullable = false, unique = true)
    private Long idUsuarioCredito;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioCredito", insertable = false, updatable = false)
    private UsuariosConCredito usuarioConCredito;
}
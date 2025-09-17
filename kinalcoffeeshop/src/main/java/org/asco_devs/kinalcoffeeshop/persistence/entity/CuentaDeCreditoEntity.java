package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CuentasDeCredito")
@Data
public class CuentaDeCreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;
    @Column(name = "saldo", precision = 10, scale = 2, nullable = false)
    private Double saldo;
//    @ManyToOne
//    @JoinColumn(name = "idUsuarioCredito", nullable = false)
    // private UsuarioConCreditoEntity idUsuarioCredito;
}

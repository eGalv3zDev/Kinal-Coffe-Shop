package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CuentasDeCredito")
@Data
public class CuentaDeCreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;
    @Column(name = "saldo", precision = 10, scale = 2, nullable = false)
    private BigDecimal saldo;
    @ManyToOne
    @JoinColumn(name = "idUsuarioCredito", nullable = false)
    private UsuarioConCreditoEntity idUsuarioCredito;
    @OneToMany(mappedBy = "idCuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDeCreditoEntity> consumos = new ArrayList<>();
}

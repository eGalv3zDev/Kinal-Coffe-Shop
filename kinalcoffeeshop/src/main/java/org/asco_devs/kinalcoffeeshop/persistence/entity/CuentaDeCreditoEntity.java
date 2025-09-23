package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(name = "saldo", precision = 3, scale = 2, nullable = false)
    private BigDecimal saldo;
    @ManyToOne
    @JoinColumn(name = "idUsuarioCredito", nullable = false)
    private UsuarioConCreditoEntity idUsuarioCredito;
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<LineaDeCreditoEntity> consumos = new ArrayList<>();
}

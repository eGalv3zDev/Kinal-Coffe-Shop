package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pagos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    @Column(name = "tipo", length = 15, nullable = false)
    private String tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido")
    private PedidoEntity idPedido;
    @OneToMany(mappedBy = "idPago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagoEntity> pagos = new ArrayList<>();
}
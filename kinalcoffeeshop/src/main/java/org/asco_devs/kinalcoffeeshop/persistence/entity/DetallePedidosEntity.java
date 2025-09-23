package org.asco_devs.kinalcoffeeshop.persistence.entity;

import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "DetallePedidos")
@Data
public class DetallePedidosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductoEntity producto;
}

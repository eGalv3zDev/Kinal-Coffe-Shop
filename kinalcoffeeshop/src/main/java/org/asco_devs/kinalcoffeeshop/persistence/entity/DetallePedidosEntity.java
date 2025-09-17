package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private PedidosEntity pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductosEntity producto;
}

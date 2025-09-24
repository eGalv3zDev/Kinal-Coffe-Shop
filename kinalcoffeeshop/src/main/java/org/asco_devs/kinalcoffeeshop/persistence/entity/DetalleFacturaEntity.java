package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DetallesFactura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleFactura;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "idFactura", nullable = false)
    private Long idFactura;

    @Column(name = "idProducto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFactura", insertable = false, updatable = false)
    private FacturasEntity factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", insertable = false, updatable = false)
    private ProductoEntity producto;
}

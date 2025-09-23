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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFactura", insertable = false, updatable = false)
    private FacturasEntity idFactura;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", insertable = false, updatable = false)
    private ProductoEntity idProducto;
}

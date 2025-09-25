package org.asco_devs.kinalcoffeeshop.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(name = "nombre", length = 128, nullable = false)
    private String nombre;
    @Column(name= "descripcion", length = 256)
    private String descripcion;
    @Column(name = "precio", precision = 3, scale = 2, nullable = false)
    private BigDecimal precio;
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    @Column(name="fecha_de_ingreso", nullable = false)
    private LocalDate fechaDeIngreso;
    @Column(name="fecha_de_expiracion", nullable = false)
    private LocalDate fechaDeExpiracion;
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaEntity idCategoria;
}

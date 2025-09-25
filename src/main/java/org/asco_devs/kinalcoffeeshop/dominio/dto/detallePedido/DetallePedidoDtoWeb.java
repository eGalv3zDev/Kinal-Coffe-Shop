package org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDtoWeb {
    Long id;
    String productName;
    Integer stock;
    Double subTotal;
    Long orderId;
    Long productId;
}

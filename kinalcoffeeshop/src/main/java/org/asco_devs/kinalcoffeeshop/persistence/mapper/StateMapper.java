package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.State;
import org.mapstruct.Named;

public class StateMapper {

    @Named("generarState")
    public static State generarState(String estado) {
        if (estado == null) return null;
        return switch (estado.toLowerCase()) {
            case "pendiente" -> State.PENDING;
            case "pagado" -> State.PAID;
            case "enviado" -> State.DISPATCHED;
            case "cancelado" -> State.CANCELLED;
            default -> null;
        };
    }

    @Named("generarEstado")
    public static String generarEstado(State state) {
        if (state == null) return null;
        return switch (state) {
            case PENDING -> "pendiente";
            case PAID -> "pagado";
            case DISPATCHED -> "enviado";
            case CANCELLED -> "cancelado";
        };
    }
}

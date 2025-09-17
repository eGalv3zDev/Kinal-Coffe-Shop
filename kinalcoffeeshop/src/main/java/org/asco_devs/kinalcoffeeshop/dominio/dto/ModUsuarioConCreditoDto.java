package org.asco_devs.kinalcoffeeshop.dominio.dto;

import java.util.Date;

public record ModUsuarioConCreditoDto(
        String name,
        String lastName,
        String phone,
        String email,
        String gender,
        Date dateBirth,
        String password
) {}
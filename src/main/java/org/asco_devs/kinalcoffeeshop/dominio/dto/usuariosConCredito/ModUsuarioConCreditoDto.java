package org.asco_devs.kinalcoffeeshop.dominio.dto.usuariosConCredito;

import java.util.Date;

public record ModUsuarioConCreditoDto(
        String name,
        String lastName,
        String phone,
        String email,
        String gender,
        Date birthDate,
        String password
) {}
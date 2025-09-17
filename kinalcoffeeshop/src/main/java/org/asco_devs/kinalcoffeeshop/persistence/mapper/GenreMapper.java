package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.Genre;
import org.mapstruct.Named;

public class GenreMapper {
    @Named("generarGenre")
    public static Genre generarGenre(String genero){
        if (genero == null) return null;
        return switch (genero.toUpperCase()){
            case "MASCULINO" -> Genre.MALE;
            case "FEMENINO" -> Genre.FEMALE;
            case "NO" -> Genre.NO;
            default -> null;
        };
    }

    @Named("generarGenero")
    public static String generarGenero(Genre genre){
        if (genre==null) return null;

        return switch (genre){
            case Genre.MALE -> "MASCULINO";
            case Genre.FEMALE -> "FEMENINO";
            case Genre.NO -> "NO";
            default -> null;
        };
    }
}

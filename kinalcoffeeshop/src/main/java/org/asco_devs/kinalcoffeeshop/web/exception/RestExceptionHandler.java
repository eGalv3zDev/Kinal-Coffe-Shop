package org.asco_devs.kinalcoffeeshop.web.exception;

import org.asco_devs.kinalcoffeeshop.dominio.exception.CategoriaAlreadyExistException;
import org.asco_devs.kinalcoffeeshop.dominio.exception.CategoriaNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.asco_devs.kinalcoffeeshop.dominio.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class RestExceptionHandler {
    @ExceptionHandler(CategoriaAlreadyExistException.class)
    public ResponseEntity<Error> handleException(CategoriaAlreadyExistException ex) {
        Error error = new Error("Categoria ya existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CategoriaNotExistException.class)
    public ResponseEntity<Error> handleException(CategoriaNotExistException ex) {
        Error error = new Error("Categoria no existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        Error error = new Error("Error desconocido", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}

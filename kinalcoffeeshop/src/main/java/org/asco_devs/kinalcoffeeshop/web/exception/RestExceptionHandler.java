package org.asco_devs.kinalcoffeeshop.web.exception;

import org.asco_devs.kinalcoffeeshop.dominio.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DetallePedidosNotExistsException.class)
    public ResponseEntity<Error> handleException(DetallePedidosNotExistsException ex) {
        Error error = new Error("detallepedido-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
package org.asco_devs.kinalcoffeeshop.web.exception;

import org.asco_devs.kinalcoffeeshop.dominio.exception.detallePedido.DetallePedidoNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.Error;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DetallePedidoNotExistException.class)
    public ResponseEntity<Error> handleException(DetallePedidoNotExistException ex) {
        Error error = new Error();
        return ResponseEntity.badRequest().body(error);
    }
}
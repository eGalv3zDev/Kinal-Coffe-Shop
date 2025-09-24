package org.asco_devs.kinalcoffeeshop.web.exception;

<<<<<<< HEAD
import org.asco_devs.kinalcoffeeshop.dominio.exception.*;
=======
import org.asco_devs.kinalcoffeeshop.dominio.exception.detallePedido.DetallePedidoNotExistException;
>>>>>>> ft/santiago
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.Error;

@RestControllerAdvice
public class RestExceptionHandler {

<<<<<<< HEAD
    @ExceptionHandler(DetallePedidosNotExistsException.class)
    public ResponseEntity<Error> handleException(DetallePedidosNotExistsException ex) {
=======
    @ExceptionHandler(DetallePedidoNotExistException.class)
    public ResponseEntity<Error> handleException(DetallePedidoNotExistException ex) {
>>>>>>> ft/santiago
        Error error = new Error();
        return ResponseEntity.badRequest().body(error);
    }
}
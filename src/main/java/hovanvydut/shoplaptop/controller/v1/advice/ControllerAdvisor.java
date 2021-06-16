package hovanvydut.shoplaptop.controller.v1.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hovanvydut
 * Created on 6/14/21
 */

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
//
//    }
}

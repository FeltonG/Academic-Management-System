package ar.edu.utn.frbb.tup.controller.handler;
import ar.edu.utn.frbb.tup.model.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UtnResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {AlumnoYaExisteException.class, ProfesorYaExisteException.class, IllegalArgumentException.class, CarreraYaExisteEstaException.class})
    protected ResponseEntity<Object> handleMateriaNotFound(
            MateriaNoEncontradaException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = {AlumnoNoEncontradoException.class, ProfesorNoEncontradoException.class, MateriaNoEncontradaException.class, CarreraNoEncontradaException.class})
    protected ResponseEntity<Object> handleMateriaNotFound(
            Exception ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(404);
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity(body, headers, status);
    }


}

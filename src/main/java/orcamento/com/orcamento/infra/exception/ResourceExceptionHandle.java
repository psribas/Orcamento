package orcamento.com.orcamento.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404 (EntityNotFoundException ex, HttpServletRequest request){

        ExceptionHandle err = new ExceptionHandle();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
        //return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400 (MethodArgumentNotValidException err){
        var error = err.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DadosError::new).toList());
    }

    public record DadosError(String message, String campo ) {

        public DadosError (FieldError err) {
            this(err.getDefaultMessage(), err.getField());
        }
    }
}

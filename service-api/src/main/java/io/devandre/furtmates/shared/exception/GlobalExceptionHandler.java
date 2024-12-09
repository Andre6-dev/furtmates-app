package io.devandre.furtmates.shared.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle validation errors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");

        // Add field-specific errors
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {
                apiError.addSubError(new ApiValidationError(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage()
                ));
            }
        });

        return buildResponseEntity(apiError);
    }

    // Handle Constraint Violations
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            apiError.addSubError(new ApiValidationError(
                    violation.getRootBeanClass().getName(),
                    violation.getPropertyPath().toString(),
                    violation.getInvalidValue(),
                    violation.getMessage()
            ));
        }

        return buildResponseEntity(apiError);
    }

    // Handle Data Integrity Violations
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage("Database error");
        apiError.setDebugMessage(ex.getMostSpecificCause().getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage("Could not find the requested resource");
        apiError.setDebugMessage(String.format("The requested URL %s %s was not found",
                ex.getHttpMethod(), ex.getRequestURL()));
        return buildResponseEntity(apiError);
    }

    // Handle Custom Business Exceptions
    @ExceptionHandler(ZeroElementsException.class)
    protected ResponseEntity<Object> handleBusinessException(ZeroElementsException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    // Handle Not Found Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    // Handle Illegal Argument Exceptions
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

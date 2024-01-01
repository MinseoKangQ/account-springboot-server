package kr.go.data.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import kr.go.data.global.exception.ErrorResponse.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<FieldError> fieldErrors = ex.getConstraintViolations().stream()
                .map(violation -> new FieldError(violation.getPropertyPath().toString(), violation.getInvalidValue().toString(), violation.getMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCode.INPUT_VALUE_INVALID.getCode())
                .message(ErrorCode.INPUT_VALUE_INVALID.getMessage())
                .status(ErrorCode.INPUT_VALUE_INVALID.getStatus())
                .errors(fieldErrors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}

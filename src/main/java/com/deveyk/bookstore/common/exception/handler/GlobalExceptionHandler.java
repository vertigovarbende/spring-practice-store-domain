package com.deveyk.bookstore.common.exception.handler;

import com.deveyk.bookstore.common.controller.response.BaseResponse;
import com.deveyk.bookstore.common.controller.response.ErrorResponse;
import com.deveyk.bookstore.common.exception.BsNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex,
                                                                 HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorResponse response = ErrorResponse.builder()
                .header(ErrorResponse.Header.VALIDATION_ERROR.name())
                .title("Invalid Request")
                .path(request.getRequestURI())
                .build();
        response.addProperty("traceId", MDC.get("traceId"));
        response.addProperty("method", request.getMethod());
        return ResponseEntity.badRequest().body(BaseResponse.of(
                HttpStatus.BAD_REQUEST,
                false,
                ex.getMessage(),
                response));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);
        Map<String, String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(HashMap::new, (map, error) -> {
                    String fieldKey = "customer.fields." + error.getField();
                    String fieldLabel = messageSource.getMessage(fieldKey, null, error.getField(), Locale.ENGLISH);

                    String validationMessage = messageSource.getMessage(error, Locale.ENGLISH);

                    map.put(fieldLabel, validationMessage);
                }, HashMap::putAll);

        ErrorResponse response = ErrorResponse.builder()
                .header(ErrorResponse.Header.VALIDATION_ERROR.name())
                .title("Validation failed")
                .path(request.getContextPath())
                .properties(details)
                .build();
        return ResponseEntity.badRequest().body(BaseResponse.of(
                HttpStatus.UNPROCESSABLE_ENTITY,
                false,
                "Invalid input data provided",
                response));
    }

    @ExceptionHandler(BsNotFoundException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(final BsNotFoundException ex,
                                                                 HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorResponse response = ErrorResponse.builder()
                .header(ErrorResponse.Header.NOT_EXISTS_ERROR.name())
                .title("Not Found")
                .path(request.getRequestURI())
                .build();
        response.addProperty("traceId", MDC.get("traceId"));
        response.addProperty("method", request.getMethod());
        return ResponseEntity.badRequest().body(BaseResponse.of(
                HttpStatus.NOT_FOUND,
                false,
                ex.getMessage(),
                response));
    }



}

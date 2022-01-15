package com.learning.deliveryapi.api.exception;

import com.learning.deliveryapi.domain.exception.BusinessException;
import com.learning.deliveryapi.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<exceptionFormat.Field> field = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            field.add(new exceptionFormat.Field(name, message));
        }

        var formattedException = new exceptionFormat();
        formattedException.setStatus(status.value());
        formattedException.setDateTime(OffsetDateTime.now());
        formattedException.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        formattedException.setFields(field);

        return handleExceptionInternal(ex, formattedException, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntityNotFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var formattedException = new exceptionFormat();
        formattedException.setStatus(status.value());
        formattedException.setDateTime(OffsetDateTime.now());
        formattedException.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, formattedException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleNegocio(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var formattedException = new exceptionFormat();
        formattedException.setStatus(status.value());
        formattedException.setDateTime(OffsetDateTime.now());
        formattedException.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, formattedException, new HttpHeaders(), status, request);
    }
}

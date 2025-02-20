package ru.ugrinovich.Spectra.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ugrinovich.Spectra.exceptions.is_already_exist.EmailAdressIsAlreadyExistException;
import ru.ugrinovich.Spectra.exceptions.not_found.ItemNotFoundBySerialNumberException;
import ru.ugrinovich.Spectra.exceptions.not_found.NotFoundException;
import ru.ugrinovich.Spectra.exceptions.violations.ErrorMessage;
import ru.ugrinovich.Spectra.exceptions.violations.ValidationErrorResponse;
import ru.ugrinovich.Spectra.exceptions.violations.Violation;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        log.error(ex.getMessage());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage onNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(ItemNotFoundBySerialNumberException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage onItemNotFoundBySerialNumberException(ItemNotFoundBySerialNumberException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(EmailAdressIsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage onEmailAdressIsAlreadyExistException(EmailAdressIsAlreadyExistException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }
}

package ru.ugrinovich.Spectra.exceptions.specific_exceptions;

public class DontHaveAnyItemsException extends RuntimeException {
    public DontHaveAnyItemsException(String message) {
        super(message);
    }
}

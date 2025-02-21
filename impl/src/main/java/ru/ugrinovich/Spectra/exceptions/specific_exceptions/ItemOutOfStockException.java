package ru.ugrinovich.Spectra.exceptions.specific_exceptions;

public class ItemOutOfStockException extends RuntimeException{
    public ItemOutOfStockException(String message) {
        super(message);
    }
}

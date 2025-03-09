package ru.ugrinovich.Spectra.exceptions.not_found;

import java.util.function.Supplier;

public class BuyersNotFoundException extends RuntimeException{
    public BuyersNotFoundException() {
        super("Покупатели не найдены");
    }

}

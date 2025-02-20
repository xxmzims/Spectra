package ru.ugrinovich.Spectra.exceptions.not_found;

import java.util.UUID;

public class ItemNotFoundBySerialNumberException extends RuntimeException {
    public ItemNotFoundBySerialNumberException(String serialNumber) {
        super("item with serialNumber " + serialNumber + " not found");
    }

}

package ru.ugrinovich.Spectra.exceptions.not_found;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID uuid, String entityName) {
        super(entityName + " with UUID " + uuid + " not found");
    }
}

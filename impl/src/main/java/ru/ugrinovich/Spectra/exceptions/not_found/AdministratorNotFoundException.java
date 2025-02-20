package ru.ugrinovich.Spectra.exceptions.not_found;

import java.util.UUID;

public class AdministratorNotFoundException extends NotFoundException {
    public AdministratorNotFoundException(UUID uuid) {
        super(uuid, "Administrator");
    }
}

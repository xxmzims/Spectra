package ru.ugrinovich.Spectra.exceptions.not_found;

import java.util.UUID;

public class ItemNotFoundException extends NotFoundException{
    public ItemNotFoundException(UUID uuid) {
        super(uuid, "Item");
    }
}

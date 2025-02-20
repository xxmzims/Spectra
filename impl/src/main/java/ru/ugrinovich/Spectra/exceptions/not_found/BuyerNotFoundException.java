package ru.ugrinovich.Spectra.exceptions.not_found;

import java.util.UUID;

public class BuyerNotFoundException extends NotFoundException{
    public BuyerNotFoundException(UUID uuid) {
        super(uuid, "Buyer");
    }
}

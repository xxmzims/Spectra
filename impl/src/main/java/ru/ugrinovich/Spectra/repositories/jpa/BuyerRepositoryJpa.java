package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ugrinovich.Spectra.entity.Buyer;

import java.util.UUID;

public interface BuyerRepositoryJpa extends JpaRepository<Buyer, UUID> {
}

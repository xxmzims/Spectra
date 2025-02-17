package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.Spectra.entity.Buyer;

import java.util.UUID;

@Repository
public interface BuyerRepositoryJpa extends JpaRepository<Buyer, UUID> {
}

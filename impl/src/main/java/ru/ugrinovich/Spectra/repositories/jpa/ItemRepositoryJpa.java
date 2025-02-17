package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.Spectra.entity.Item;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepositoryJpa extends JpaRepository<Item, UUID> {
    Optional<Item> findBySerialNumber(String serialNumber);
}

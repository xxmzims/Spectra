package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.Spectra.entities.Item;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepositoryJpa extends JpaRepository<Item, UUID>, JpaSpecificationExecutor<Item> {
    Optional<Item> findBySerialNumber(String serialNumber);
}

package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ugrinovich.Spectra.entity.Item;

import java.util.UUID;

public interface ItemRepositoryJpa extends JpaRepository<Item, UUID> {
}

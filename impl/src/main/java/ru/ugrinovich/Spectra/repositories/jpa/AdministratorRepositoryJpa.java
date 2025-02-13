package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ugrinovich.Spectra.entity.Administrator;

import java.util.UUID;

public interface AdministratorRepositoryJpa extends JpaRepository<Administrator, UUID> {
}

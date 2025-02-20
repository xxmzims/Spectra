package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.Spectra.entities.Administrator;

import java.util.UUID;
@Repository
public interface AdministratorRepositoryJpa extends JpaRepository<Administrator, UUID> {
}

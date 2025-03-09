package ru.ugrinovich.Spectra.services.administrator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entities.Administrator;
import ru.ugrinovich.Spectra.exceptions.not_found.AdministratorNotFoundException;
import ru.ugrinovich.Spectra.repositories.jpa.AdministratorRepositoryJpa;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "false", matchIfMissing = true)
@Slf4j
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepositoryJpa administratorRepositoryJpa;

    @Override
    public List<Administrator> findAllAdministrators() {
        return administratorRepositoryJpa.findAll();
    }

    @Override
    public Administrator findById(UUID id) {
        return administratorRepositoryJpa.findById(id).orElseThrow(() -> new AdministratorNotFoundException(id));
    }

    @Override
    public void save(Administrator administrator) {
        administratorRepositoryJpa.save(administrator);
    }

    @Override
    public void deleteById(UUID id) {
        findById(id);
        administratorRepositoryJpa.deleteById(id);
    }

    @Override
    public Administrator updateById(UUID id, Administrator newAdministrator) {

        log.trace("Попыткка найти администратора с id {} в базе данных", id);
        Administrator foundAdmin = findById(id);
        log.trace("Администратор с id {} найден в БД", id);
        AdministratorUtil.updateDataAdmin(foundAdmin, newAdministrator);

        administratorRepositoryJpa.save(foundAdmin);
        log.trace("Изменения для администратора с id {} приняты", id);
        return foundAdmin;
    }
}

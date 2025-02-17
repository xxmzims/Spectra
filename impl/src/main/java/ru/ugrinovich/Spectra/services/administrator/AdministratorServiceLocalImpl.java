package ru.ugrinovich.Spectra.services.administrator;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entity.Administrator;
import ru.ugrinovich.Spectra.repositories.local.AdministratorRepositoryLocal;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "true")
public class AdministratorServiceLocalImpl implements AdministratorService{
    private final AdministratorRepositoryLocal administratorRepository;

    public List<Administrator> findAllAdministrators() {
        return administratorRepository.findAll();
    }

    public Administrator findById(UUID id) {
        return administratorRepository.findById(id).orElse(null);
    }

    public void save(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    public void deleteById(UUID id) {
        administratorRepository.deleteById(id);
    }

    public void updateById(UUID id, Administrator administrator) {
        administratorRepository.updateById(id, administrator);
    }
}

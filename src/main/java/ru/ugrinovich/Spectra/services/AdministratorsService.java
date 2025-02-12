package ru.ugrinovich.Spectra.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.models.Administrator;
import ru.ugrinovich.Spectra.repositories.AdministratorRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdministratorsService {
    private final AdministratorRepository administratorRepository;

    public List<Administrator> findAllAdministrators() {
        return administratorRepository.findAll();
    }

    public Administrator findById(int id) {
        return administratorRepository.findById(id).orElse(null);
    }

    public void save(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    public void deleteById(int id) {
        administratorRepository.deleteById(id);
    }

    public void updateById(int id, Administrator administrator) {
        administratorRepository.updateById(id, administrator);
    }
}

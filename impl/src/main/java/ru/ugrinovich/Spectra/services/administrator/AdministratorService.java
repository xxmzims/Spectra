package ru.ugrinovich.Spectra.services.administrator;

import ru.ugrinovich.Spectra.entities.Administrator;

import java.util.List;
import java.util.UUID;

public interface AdministratorService {
     List<Administrator> findAllAdministrators();

     Administrator findById(UUID id);

     void save(Administrator administrator);

     void deleteById(UUID id);

     Administrator updateById(UUID id, Administrator administrator);
}

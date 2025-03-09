package ru.ugrinovich.Spectra.services.administrator;

import lombok.experimental.UtilityClass;
import ru.ugrinovich.Spectra.entities.Administrator;


@UtilityClass
public class AdministratorUtil {

    public void updateDataAdmin(Administrator foundAdministrator, Administrator updatedAdministrator) {
        if (updatedAdministrator.getName() != null) {
            foundAdministrator.setName(updatedAdministrator.getName());
        }
    }
}

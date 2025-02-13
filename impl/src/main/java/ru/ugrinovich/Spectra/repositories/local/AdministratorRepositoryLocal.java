package ru.ugrinovich.Spectra.repositories.local;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entity.Administrator;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdministratorRepositoryLocal {

    private final List<Administrator> administrators = new ArrayList<>();

    public void save(Administrator administrator){
        administrators.add(administrator);
    }
    public List<Administrator> findAll(){
        return administrators;
    }

    public void deleteById(UUID id){
        Administrator administrator = findById(id).get();
        administrators.remove(administrator);
    }
    public void updateById(UUID id, Administrator updatedAdministrator){
        administrators.stream().filter(administrator -> administrator.
                getAdminId().equals(id))
                .findFirst()
                .map(administrator -> updatedAdministrator);
    }
    public Optional<Administrator> findById(UUID id){
        return administrators.stream().
                filter(admin -> admin.getAdminId().equals(id)).findAny();
    }
}

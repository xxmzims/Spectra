package ru.ugrinovich.Spectra.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.models.Administrator;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdministratorRepository {
    private int id = 0;
    private List<Administrator> administrators = new ArrayList<>();

    public void save(Administrator administrator){
        administrator.setId(++id);
        administrators.add(administrator);
    }
    public List<Administrator> findAll(){
        return administrators;
    }

    public void deleteById(int id){
        Administrator administrator = findById(id).get();
        administrators.remove(administrator);
    }
    public void updateById(int id, Administrator administrator){
        Administrator administratorUpdated = findById(id).get();
        administratorUpdated.setName(administrator.getName());
    }
    public Optional<Administrator> findById(int id){
        return administrators.stream().filter(x -> x.getId() == id).findAny();
    }
}

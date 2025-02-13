package ru.ugrinovich.Spectra.repositories.local;


import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemRepositoryLocal {
    private final List<Item> items = new ArrayList<>();

    public void save(Item item){
        items.add(item);
    }
    public List<Item> findAll(){
        return items;
    }
    public void deleteById(UUID id){
        Item item = findById(id).get();
        items.remove(item);
    }
    public void updateById(UUID id, Item updatedItem){
        items.stream().filter(item -> item.getId().equals(id)).findFirst().map(item -> updatedItem);
    }
    public Optional<Item> findById(UUID id){
        return items.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    public Optional<Item> findBySerialNumber(String serialNumber){
        return items.stream().filter(x -> x.getSerialNumber().equals(serialNumber)).findAny();
    }
}

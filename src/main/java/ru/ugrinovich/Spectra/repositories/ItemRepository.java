package ru.ugrinovich.Spectra.repositories;


import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ItemRepository {
    private int id = 0;
    private List<Item> items = new ArrayList<>();

    public void save(Item item){
        item.setId(++id);
        items.add(item);
    }
    public List<Item> findAll(){
        return items;
    }
    public void deleteById(int id){
        Item item = findById(id).get();
        items.remove(item);
    }
    public void updateById(int id, Item item){
        Item itemUpdated = findById(id).get();
        itemUpdated.setName(item.getName());
        itemUpdated.setDescription(item.getDescription());
        itemUpdated.setSerialNumber(item.getSerialNumber());
        itemUpdated.setCategory(item.getCategory());
    }
    public Optional<Item> findById(int id){
        return items.stream().filter(x -> x.getId() == id).findAny();
    }

    public Optional<Item> findBySerialNumber(String serialNumber){
        return items.stream().filter(x -> x.getSerialNumber().equals(serialNumber)).findAny();
    }
}

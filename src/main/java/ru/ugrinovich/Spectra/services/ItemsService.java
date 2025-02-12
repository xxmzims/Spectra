package ru.ugrinovich.Spectra.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.models.Item;
import ru.ugrinovich.Spectra.repositories.ItemRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemsService {

    public final ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item getItemById(int id){
        return itemRepository.findById(id).orElse(null);
    }


    public void deleteById(int id){
        itemRepository.deleteById(id);
    }


    public void save(Item item){
        itemRepository.save(item);
    }

    public void updateById(int id, Item item){
        itemRepository.updateById(id, item);
    }

    public Item findBySerialNumber(String serialNumber){
        return itemRepository.findBySerialNumber(serialNumber).orElse(null);
    }
}

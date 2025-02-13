package ru.ugrinovich.Spectra.services.item;

import ru.ugrinovich.Spectra.entity.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    List<Item> getAllItems();

    Item getItemById(UUID id);

    void deleteById(UUID id);

    void save(Item item);

    void updateById(UUID id, Item item);

    Item findBySerialNumber(String serialNumber);
}

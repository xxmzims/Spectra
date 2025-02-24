package ru.ugrinovich.Spectra.services.item;

import org.springframework.data.domain.Page;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    List<ItemPurchase> getAllOffers();

    void save(List<Item> items);

    Page<ItemResponse> getAllItemWithSpecAndPag(ItemFilterRequest itemFilterRequest);

    List<Item> getAllItems();

    Item findById(UUID id);

    void deleteById(UUID id);

    void save(Item item);

    void updateById(UUID id, Item item);

    Item findBySerialNumber(String serialNumber);
}

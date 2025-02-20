package ru.ugrinovich.Spectra.services.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    Page<ItemResponse> getAllItemsWithPagination(PageRequest pageRequest);

    List<ItemResponse> getAllItemWithSpecification(Specification<Item> specification);

    List<Item> getAllItems();

    Item findById(UUID id);

    void deleteById(UUID id);

    void save(Item item);

    void updateById(UUID id, Item item);

    Item findBySerialNumber(String serialNumber);
}

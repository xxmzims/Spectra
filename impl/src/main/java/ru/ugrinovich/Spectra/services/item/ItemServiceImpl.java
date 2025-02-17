package ru.ugrinovich.Spectra.services.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entity.Item;
import ru.ugrinovich.Spectra.repositories.jpa.ItemRepositoryJpa;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "false")
public class ItemServiceImpl implements ItemService {
    private final ItemRepositoryJpa itemRepositoryJpa;

    @Override
    public List<Item> getAllItems() {
        return itemRepositoryJpa.findAll();
    }

    @Override
    public Item getItemById(UUID id) {
        return itemRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        itemRepositoryJpa.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepositoryJpa.save(item);
    }

    @Override
    public void updateById(UUID id, Item item) {
        item.setId(id);
        itemRepositoryJpa.save(item);
    }

    @Override
    public Item findBySerialNumber(String serialNumber) {
        return itemRepositoryJpa.findBySerialNumber(serialNumber).orElse(null);
    }
}

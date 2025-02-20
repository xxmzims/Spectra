package ru.ugrinovich.Spectra.services.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.exceptions.not_found.ItemNotFoundBySerialNumberException;
import ru.ugrinovich.Spectra.exceptions.not_found.ItemNotFoundException;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.repositories.jpa.ItemRepositoryJpa;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "false")
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepositoryJpa itemRepositoryJpa;

    @Override
    public List<Item> getAllItems() {
        return itemRepositoryJpa.findAll();
    }

    @Override
    public Page<ItemResponse> getAllItemsWithPagination(PageRequest pageRequest){
        Page<Item> items = itemRepositoryJpa.findAll(pageRequest);
        return new PageImpl<>(
                items.getContent().stream()
                        .map(itemMapper::toItemResponse)
                        .collect(Collectors.toList()),
                items.getPageable(),
                items.getTotalElements());
    }

    @Override
    public List<ItemResponse> getAllItemWithSpecification(Specification<Item> spec) {
        List<Item> items = itemRepositoryJpa.findAll(spec);
        return itemMapper.toItemResponseList(items);
    }

    public Page<Item> getAllItemWithSpecAndPag(Specification<Item> spec, PageRequest pageRequest){
        Page<Item> items = itemRepositoryJpa.findAll(spec, pageRequest);
        return items;
    }

    @Override
    public Item findById(UUID id) {
        return itemRepositoryJpa.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public void deleteById(UUID id) {
        findById(id);
        itemRepositoryJpa.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepositoryJpa.save(item);
    }

    @Override
    public void updateById(UUID id, Item item) {
        findById(id);
        item.setId(id);
        itemRepositoryJpa.save(item);
    }

    @Override
    public Item findBySerialNumber(String serialNumber) {
        return itemRepositoryJpa.findBySerialNumber(serialNumber).orElseThrow(() -> new ItemNotFoundBySerialNumberException(serialNumber));
    }
}

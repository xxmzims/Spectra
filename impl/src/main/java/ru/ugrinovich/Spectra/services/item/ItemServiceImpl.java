package ru.ugrinovich.Spectra.services.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.exceptions.is_already_exist.SerialNumberIsAlreadyExistException;
import ru.ugrinovich.Spectra.exceptions.not_found.ItemNotFoundBySerialNumberException;
import ru.ugrinovich.Spectra.exceptions.not_found.ItemNotFoundException;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.repositories.jpa.ItemRepositoryJpa;
import ru.ugrinovich.Spectra.repositories.jpa.PurchaseHistoryJpa;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.specification.ItemSpecification;

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
    private final ItemSpecification itemSpecification;
    private final PurchaseHistoryJpa purchaseHistoryJpa;

    @Override
    public List<Item> getAllItems() {
        return itemRepositoryJpa.findAll();
    }

    public Page<ItemResponse> getAllItemWithSpecAndPag(ItemFilterRequest filter){

        Specification<Item> spec = itemSpecification.toSpecForAllItems(filter);

        Page<Item> items = itemRepositoryJpa.findAll(spec, PageRequest.of(filter.getOffset(), filter.getLimit(), filter.getOrder().getSortValue()));
        return new PageImpl<>(
                items.getContent().stream()
                        .map(itemMapper::toItemResponse)
                        .collect(Collectors.toList()),
                items.getPageable(),
                items.getTotalElements());
    }

    @Override
    public Item findById(UUID id) {
        return itemRepositoryJpa.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public List<ItemPurchase> getAllOffers(){
        return purchaseHistoryJpa.findAll();
    }

    @Override
    public void save(List<Item> items) {
        itemRepositoryJpa.saveAll(items);
    }

    @Override
    public void deleteById(UUID id) {
        findById(id);
        itemRepositoryJpa.deleteById(id);
    }

    @Override
    public void save(Item item) {
        String serialNumber = item.getSerialNumber();
        try {
            findBySerialNumber(serialNumber);
        }catch (ItemNotFoundBySerialNumberException ex){
            itemRepositoryJpa.save(item);
            return;
        }
        throw new SerialNumberIsAlreadyExistException(serialNumber);
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

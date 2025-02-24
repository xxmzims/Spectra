package ru.ugrinovich.Spectra.services.item;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.repositories.local.ItemRepositoryLocal;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "true")
public class ItemServiceLocalImpl implements ItemService{

    private final ItemRepositoryLocal itemRepository;

    @Override
    public Page<ItemResponse> getAllItemWithSpecAndPag(ItemFilterRequest itemFilterRequest) {
        return null;
    }

    @Override
    public void save(List<Item> items) {

    }

    @Override
    public List<ItemPurchase> getAllOffers() {
        return null;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item findById(UUID id){
        return itemRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id){
        itemRepository.deleteById(id);
    }

    public void save(Item item){
        itemRepository.save(item);
    }

    public void updateById(UUID id, Item item){
        itemRepository.updateById(id, item);
    }

    public Item findBySerialNumber(String serialNumber){
        return itemRepository.findBySerialNumber(serialNumber).orElse(null);
    }
}

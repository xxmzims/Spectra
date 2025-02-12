package ru.ugrinovich.Spectra.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.models.Buyer;
import ru.ugrinovich.Spectra.models.Item;
import ru.ugrinovich.Spectra.repositories.BuyerRepository;
import ru.ugrinovich.Spectra.repositories.ItemRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuyersService {

    private final ItemRepository itemRepository;
    private final BuyerRepository buyerRepository;

    public List<Buyer> findAllBuyers() {
        return buyerRepository.findAll();
    }

    public Buyer findById(int id) {
        return buyerRepository.findById(id).orElse(null);
    }

    public void save(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void deleteById(int id) {
        buyerRepository.deleteById(id);
    }

    public void updateById(int id, Buyer buyer) {
        buyerRepository.updateById(id, buyer);
    }

    public void assignItemToBuyer(int id, int item_id) {
        Item item = itemRepository.findById(item_id).get();
        buyerRepository.assignItemByBuyerId(id, item);
    }
}

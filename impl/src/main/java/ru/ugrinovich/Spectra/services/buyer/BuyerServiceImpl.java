package ru.ugrinovich.Spectra.services.buyer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entity.Buyer;
import ru.ugrinovich.Spectra.entity.Item;
import ru.ugrinovich.Spectra.repositories.local.BuyerRepositoryLocal;
import ru.ugrinovich.Spectra.repositories.local.ItemRepositoryLocal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Service
public class BuyerServiceImpl implements BuyerService {

    private final ItemRepositoryLocal itemRepository;
    private final BuyerRepositoryLocal buyerRepository;

    public List<Buyer> findAllBuyers() {
        return buyerRepository.findAll();
    }

    public Buyer findById(UUID id) {
        return buyerRepository.findById(id).orElse(null);
    }

    public void save(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void deleteById(UUID id) {
        buyerRepository.deleteById(id);
    }

    public void updateById(UUID id, Buyer buyer) {
        buyerRepository.updateById(id, buyer);
    }

    public void assignItemToBuyer(UUID id, UUID item_id) {
        Optional<Item> optionalItem = itemRepository.findById(item_id);
        optionalItem.ifPresent(item -> buyerRepository.assignItemByBuyerId(id, item));

    }
}

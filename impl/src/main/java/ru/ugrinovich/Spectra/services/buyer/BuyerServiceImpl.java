package ru.ugrinovich.Spectra.services.buyer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entity.Buyer;
import ru.ugrinovich.Spectra.repositories.jpa.BuyerRepositoryJpa;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "false")
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepositoryJpa buyerRepositoryJpa;

    @Override
    public List<Buyer> findAllBuyers() {
        return buyerRepositoryJpa.findAll();
    }

    @Override
    public Buyer findById(UUID id) {
        return buyerRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void save(Buyer buyer) {
        buyerRepositoryJpa.save(buyer);
    }

    @Override
    public void deleteById(UUID id) {
        buyerRepositoryJpa.deleteById(id);
    }

    @Override
    public void updateById(UUID id, Buyer buyer) {
        buyer.setId(id);
        buyerRepositoryJpa.save(buyer);
    }

    @Override
    public void assignItemToBuyer(UUID id, UUID item_id) {
        Buyer buyer = findById(id);
    }
}

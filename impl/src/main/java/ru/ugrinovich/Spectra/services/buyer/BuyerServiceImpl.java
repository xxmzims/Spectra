package ru.ugrinovich.Spectra.services.buyer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.exceptions.is_already_exist.EmailAdressIsAlreadyExistException;
import ru.ugrinovich.Spectra.exceptions.not_found.BuyerNotFoundException;
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
        return buyerRepositoryJpa.findById(id).orElseThrow(() -> new BuyerNotFoundException(id));
    }

    @Override
    public void save(Buyer buyer) {
        checkExistEmail(buyer.getEmail());
        buyerRepositoryJpa.save(buyer);
    }

    @Override
    public void deleteById(UUID id) {
        findById(id);
        buyerRepositoryJpa.deleteById(id);
    }

    @Override
    public void updateById(UUID id, Buyer buyer) {
        findById(id);
        buyer.setId(id);
        checkExistEmail(buyer.getEmail());
        buyerRepositoryJpa.save(buyer);
    }

    @Override
    public void assignItemToBuyer(UUID id, UUID item_id) {
        // TODO
    }
    public void checkExistEmail(String email){
        buyerRepositoryJpa.findBuyerByEmail(email).ifPresent(client -> {
            throw new EmailAdressIsAlreadyExistException(email);
        });
    }
}

package ru.ugrinovich.Spectra.services.buyer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;

import java.util.List;
import java.util.UUID;

public interface BuyerService {

    List<Buyer> findAllBuyers();

    Buyer findById(UUID id);

    void save(Buyer buyer);

    void deleteById(UUID id);

    void updateById(UUID id, Buyer buyer);

    void assignItemToBuyer(UUID id, UUID item_id);
}

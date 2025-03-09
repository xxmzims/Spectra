package ru.ugrinovich.Spectra.services.buyer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.ItemOutOfStockException;
import ru.ugrinovich.Spectra.repositories.local.BuyerRepositoryLocal;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerWithItemsResponse;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "true")
public class BuyerServiceLocalImpl implements BuyerService {

    @Override
    public List<ForAdministratorBuyerWithItemsResponse> findAllBuyersWithItems() {
        return null;
    }
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

    @Override
    public void addItemToPurchaseList(ForAddItemToPurchaseListRequest forAddItemToPurchaseListRequest) throws ItemOutOfStockException {
    }
    @Override
    public List<ItemPurchase> findHistoryOfPurchases(ForGetHistoryOfPurchaseRequest request) {
        return null;
    }
}

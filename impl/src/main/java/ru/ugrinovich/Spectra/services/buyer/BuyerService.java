package ru.ugrinovich.Spectra.services.buyer;

import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.ItemOutOfStockException;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerWithItemsResponse;

import java.util.List;
import java.util.UUID;

public interface BuyerService {

    List<ForAdministratorBuyerWithItemsResponse> findAllBuyersWithItems();

    List<Buyer> findAllBuyers();

    Buyer findById(UUID id);

    void save(Buyer buyer);

    void deleteById(UUID id);

    void updateById(UUID id, Buyer buyer);

    void addItemToPurchaseList(ForAddItemToPurchaseListRequest forAddItemToPurchaseListRequest) throws ItemOutOfStockException;

    List<ItemPurchase> findHistoryOfPurchases(ForGetHistoryOfPurchaseRequest request);
}

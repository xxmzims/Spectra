package ru.ugrinovich.Spectra.services.buyer;

import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.ItemOutOfStockException;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;

import java.util.List;
import java.util.UUID;

public interface BuyerService {

    List<Buyer> findAllBuyers();

    Buyer findById(UUID id);

    void save(Buyer buyer);

    void deleteById(UUID id);

    void updateById(UUID id, Buyer buyer);

    void addItemToPurchaseList(ForAddItemToPurchaseListRequest forAddItemToPurchaseListRequest) throws ItemOutOfStockException;

    List<Item> findHistoryOfPurchases(ForGetHistoryOfPurchaseRequest request);
}

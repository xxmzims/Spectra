package ru.ugrinovich.Spectra.services.buyer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.exceptions.is_already_exist.EmailAdressIsAlreadyExistException;
import ru.ugrinovich.Spectra.exceptions.not_found.BuyerNotFoundException;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.DontHaveAnyItemsException;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.ItemOutOfStockException;
import ru.ugrinovich.Spectra.repositories.jpa.BuyerRepositoryJpa;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.request.Item.ItemPurchaseStatus;
import ru.ugrinovich.Spectra.services.item.ItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "false")
public class BuyerServiceImpl implements BuyerService {

    private final ItemService itemService;
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
    public void addItemToPurchaseList(ForAddItemToPurchaseListRequest request) {
        Buyer buyer = findById(request.getBuyerId());
        Item item = itemService.findById(request.getItemId());
        Integer amount = item.getAmount();
        if (request.getItemPurchaseStatus().equals(ItemPurchaseStatus.BOUGHT)) {
            if (amount.equals(0)) {
                throw new ItemOutOfStockException("Товара нет в наличии ");
            }
            item.setAmount(amount - 1);
        }
        item.setPurchaseStatus(request.getItemPurchaseStatus());
        item.setBuyer(buyer);
    }

    public void checkExistEmail(String email) {
        buyerRepositoryJpa.findBuyerByEmail(email).ifPresent(client -> {
            throw new EmailAdressIsAlreadyExistException(email);
        });
    }

    @Override
    public List<Item> findHistoryOfPurchases(ForGetHistoryOfPurchaseRequest request) {
        Buyer buyer = findById(request.getBuyerId());
        List<Item> items = buyer.getItems();
        if(items != null)
            return items.stream().filter(x -> x.getPurchaseStatus().equals(request.getItemPurchaseStatus())).collect(Collectors.toList());
        else
            throw new DontHaveAnyItemsException("У этого пользователя нет товаров");

    }
}

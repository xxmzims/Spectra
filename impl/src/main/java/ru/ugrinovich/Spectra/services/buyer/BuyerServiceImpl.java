package ru.ugrinovich.Spectra.services.buyer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.exceptions.is_already_exist.EmailAdressIsAlreadyExistException;
import ru.ugrinovich.Spectra.exceptions.not_found.BuyerNotFoundException;
import ru.ugrinovich.Spectra.exceptions.not_found.BuyersNotFoundException;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.DontHaveAnyItemsException;
import ru.ugrinovich.Spectra.exceptions.specific_exceptions.ItemOutOfStockException;
import ru.ugrinovich.Spectra.mappers.BuyerMapper;
import ru.ugrinovich.Spectra.repositories.jpa.BuyerRepositoryJpa;
import ru.ugrinovich.Spectra.repositories.jpa.PurchaseHistoryJpa;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerWithItemsResponse;
import ru.ugrinovich.Spectra.services.item.ItemService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
@ConditionalOnProperty(name = "app.in-memory-model.enabled", havingValue = "false")
public class BuyerServiceImpl implements BuyerService {

    private final ItemService itemService;
    private final BuyerMapper buyerMapper;
    private final BuyerRepositoryJpa buyerRepositoryJpa;
    private final PurchaseHistoryJpa purchaseHistoryJpa;

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
        int amount = item.getAmount();
        if (amount < request.getQuantity()) {
            throw new ItemOutOfStockException("Товара нет в наличии. Количество оставшегося товара: " + amount);
        }
        item.setAmount(amount - request.getQuantity());
        ItemPurchase itemPurchase = ItemPurchase.builder()
                .buyer(buyer)
                .item(item)
                .quantity(request.getQuantity())
                .totalPrice((request.getPrice()) * request.getQuantity())
                .build();
        purchaseHistoryJpa.save(itemPurchase);
    }

    private void checkExistEmail(String email) {
        buyerRepositoryJpa.findBuyerByEmail(email).ifPresent(client -> {
            throw new EmailAdressIsAlreadyExistException(email);
        });
    }

    @Override
    public List<ItemPurchase> findHistoryOfPurchases(ForGetHistoryOfPurchaseRequest request) {
        return purchaseHistoryJpa.findByBuyer(findById(request.getBuyerId())).orElseThrow(() -> new DontHaveAnyItemsException("У пользователя нет товаров"));
    }



    public List<ForAdministratorBuyerWithItemsResponse> findAllBuyersWithItems() {
        log.warn("Попытка найти покупателей в БД");
        List<Buyer> buyers = buyerRepositoryJpa.findAllBuyersWithPurchases().orElseThrow(BuyersNotFoundException::new);
        return buyerMapper.toForAdministratorBuyerWithItemsResponse(buyers);
}
}

package ru.ugrinovich.Spectra.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import ru.ugrinovich.Spectra.API.BuyerAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.mappers.BuyerMapper;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.mappers.PurchaseHistoryMapper;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.request.Item.*;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.services.buyer.BuyerService;
import ru.ugrinovich.Spectra.services.item.ItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BuyerController implements BuyerAPI {

    private final BuyerService buyersService;
    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final BuyerMapper buyerMapper;
    private final PurchaseHistoryMapper purchaseHistoryMapper;

    public ResponseEntity<List<BuyerResponse>> findAllBuyers() {
        List<Buyer> buyers = buyersService.findAllBuyers();
        log.info("Найдены покупатели с id {}", buyers.stream().map(Buyer::getId).collect(Collectors.toList()));

        return ResponseEntity.ok(buyerMapper.toBuyerResponses(buyers));
    }

    @Override
    public ResponseEntity<HttpStatus> addItemToPurchaseList(ForAddItemToPurchaseListRequest request) {
        buyersService.addItemToPurchaseList(request);
        log.info("Товар с id {} добавлен в список покупок Покупателю с id {}", request.getItemId(), request.getBuyerId());
        return ResponseEntity.ok(ACCEPTED);
    }

    @Override
    public ResponseEntity<Page<ItemResponse>> getItems(ItemFilterRequest itemFilterRequest) {
        Page<ItemResponse> itemResponses = itemService.getAllItemWithSpecAndPag(itemFilterRequest);
        log.info("Найдены товары с серийными номерами {}", itemResponses.stream().map(ItemResponse::getSerialNumber).collect(Collectors.toList()));
        return ResponseEntity.ok(itemResponses);
    }

    public ResponseEntity<ItemResponse> getItem(UUID id) {
        Item item = itemService.findById(id);
        log.info("Найден товар для покупателя с id {}", id);
        return ResponseEntity.ok(itemMapper.toItemResponse(item));
    }

    public ResponseEntity<BuyerResponse> createBuyer(BuyerCreateRequest buyerCreateRequest) {
        Buyer buyer = buyerMapper.toBuyer(buyerCreateRequest);
        buyersService.save(buyer);
        log.info("Создан покупатель с id {}", buyer.getId());
        return new ResponseEntity<>(buyerMapper.toBuyerResponse(buyer), CREATED);
    }

    public ResponseEntity<BuyerResponse> getBuyer(UUID id) {
        Buyer buyer = buyersService.findById(id);
        log.info("Найден покупатель с id {}", buyer.getId());

        return ResponseEntity.ok(buyerMapper.toBuyerResponse(buyer));
    }

    public ResponseEntity<HttpStatus> deleteBuyer(UUID id) {
        buyersService.deleteById(id);
        log.info("Удален покупатель с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<BuyerResponse> updateBuyer(UUID id, BuyerUpdateRequest buyerUpdateRequest) {
        Buyer buyer = buyerMapper.toBuyer(buyerUpdateRequest);
        buyersService.updateById(id, buyer);
        log.info("Обновлены данные покупателя с id {}", id);
        return new ResponseEntity<>(buyerMapper.toBuyerResponse(buyer), ACCEPTED);
    }

    @Override
    public ResponseEntity<List<ItemPurchaseHistoryResponse>> getHistoryOfPleasures(ForGetHistoryOfPurchaseRequest request) {

        List<ItemPurchase> purchases = buyersService.findHistoryOfPurchases(request);

        log.info("Найдены товары {} в истории покупателя с id  {}", purchases.stream().map(ItemPurchase::getItem).map(Item::getId).collect(Collectors.toList()), request.getBuyerId());
        return ResponseEntity.ok(purchaseHistoryMapper.toResponse(purchases));
    }
}

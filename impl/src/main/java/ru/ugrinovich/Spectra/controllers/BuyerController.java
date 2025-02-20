package ru.ugrinovich.Spectra.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.ugrinovich.Spectra.API.BuyerAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.mappers.BuyerMapper;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseStatus;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.response.Item.ItemType;
import ru.ugrinovich.Spectra.response.Item.ItemViewStatus;
import ru.ugrinovich.Spectra.services.buyer.BuyerService;
import ru.ugrinovich.Spectra.services.item.ItemService;
import ru.ugrinovich.Spectra.specification.ItemSpecification;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BuyerController implements BuyerAPI {

    private final ItemSpecification itemSpecification;
    private final BuyerService buyersService;
    private final ItemService itemService;
    private final BuyerMapper buyerMapper;

    public ResponseEntity<List<BuyerResponse>> findAllBuyers() {
        List<Buyer> buyers = buyersService.findAllBuyers();
        log.info("Найдены покупатели с id {}", buyers.stream().map(Buyer::getId).collect(Collectors.toList()));

        return ResponseEntity.ok(buyerMapper.toBuyerResponses(buyers));
    }

    @Override
    public ResponseEntity<Page<ItemResponse>> getItems(Integer offset, Integer limit) {
        Page<ItemResponse> items = itemService.getAllItemsWithPagination(PageRequest.of(offset, limit));
        log.info("Найдены товары на странице {} с серийными номерами {}", offset, items.stream().map(ItemResponse::getSerialNumber).collect(Collectors.toList()));
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<List<ItemResponse>> searchItems(ItemType category,
                                                          ItemViewStatus viewStatus,
                                                          ItemPurchaseStatus itemPurchaseStatus,
                                                          Integer amount,
                                                          Double startPrice,
                                                          Double endPrice) {

        Specification<Item> spec = Specification.where(null);
        if (category != null) spec = spec.and(itemSpecification.hasCategory(category));
        if (viewStatus != null) spec = spec.and(itemSpecification.hasStatusView(viewStatus));
        if (itemPurchaseStatus != null) spec = spec.and(itemSpecification.hasStatusPurchase(itemPurchaseStatus));
        if (amount != null) spec = spec.and(itemSpecification.hasMinAmount(amount));
        if(startPrice != null && endPrice == null) spec = spec.and(itemSpecification.hasStartPrice(startPrice));
        if(endPrice != null && startPrice == null) spec = spec.and(itemSpecification.hasEndPrice(endPrice));
        if (endPrice != null && startPrice != null) spec = spec.and(itemSpecification.hasEndPriceAndStartPrice(startPrice, endPrice));
            return ResponseEntity.ok(itemService.getAllItemWithSpecification(spec));
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


    public ResponseEntity<BuyerResponse> assignItem(UUID id, UUID item_id) {
        buyersService.assignItemToBuyer(id, item_id);
        log.info("Для покупателя с id {} назначен товар с id {}", id, item_id);
        return ResponseEntity.ok(new BuyerResponse());
    }
}

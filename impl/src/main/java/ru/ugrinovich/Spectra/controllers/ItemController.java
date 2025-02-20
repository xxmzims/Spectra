package ru.ugrinovich.Spectra.controllers;

import lombok.extern.slf4j.Slf4j;
import ru.ugrinovich.Spectra.API.ItemAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.dto.ItemDTO;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemUpdateRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.services.item.ItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController implements ItemAPI {

    private final ItemMapper itemMapper;
    private final ItemService itemsService;

    public List<ItemResponse> getAllItems() {
        List<Item> items = itemsService.getAllItems();
        log.info("Найдены товары с id {}", items.stream().map(Item::getId).collect(Collectors.toList()));
        return itemMapper.toItemResponseList(items);
    }

    public ResponseEntity<ItemResponse> createItem(ItemCreateRequest itemCreateRequest) {
        Item item = itemMapper.toItem(itemCreateRequest);
        itemsService.save(item);
        log.info("Создан товар с id {}", item.getId());
        return new ResponseEntity<>(itemMapper.toItemResponse(item), CREATED);
    }

    public ResponseEntity<ItemResponse> getItem(UUID id) {
        Item item = itemsService.findById(id);
        log.info("Найден товар с id {}", id);
        return ResponseEntity.ok(itemMapper.toItemResponse(item));
    }

    public ResponseEntity<ItemResponse> updateItem(UUID id, ItemUpdateRequest itemUpdateRequest) {
        ItemDTO itemDTO = itemMapper.toItemDTO(itemUpdateRequest);
        Item item = itemMapper.toItem(itemDTO);
        itemsService.updateById(id, item);
        log.info("Обновлены данные товара с id {}", id);
        return new ResponseEntity<>(itemMapper.toItemResponse(item), ACCEPTED);
    }

    public ResponseEntity<HttpStatus> deleteItem(UUID id) {
        itemsService.deleteById(id);
        log.info("Удален товар с id {}", id);
        return ResponseEntity.ok(ACCEPTED);
    }

    public ResponseEntity<ItemResponse> getItem(String serialNumber) {
        Item item = itemsService.findBySerialNumber(serialNumber);

        log.info("Найден товар с серийным номером {}" , serialNumber);
        return ResponseEntity.ok(itemMapper.toItemResponse(item));
    }
}
